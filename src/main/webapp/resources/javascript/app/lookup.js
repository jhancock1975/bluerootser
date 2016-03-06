// CSS-related constants. Should be synced with frame.css.
var ROOT_ID = 'chrome_ggl_dict_ext';
var FORM_ID = ROOT_ID + '_form';
var PADDING_LEFT = 10;
var PADDING_RIGHT = 0;
var PADDING_TOP = 15;
var PADDING_BOTTOM = 15;
var PADDING_FORM = 10;
var BASE_Z_INDEX = 65000;
// URL constants.
var EXTERN_LINK_TEMPLATE = 'http://en.wiktionary.org/wiki/%query%';
var AUDIO_LINK_TEMPLATE = 'http://en.wiktionary.org/wiki/File:%file%';
var GOOGLE_DICT_LINK_TEMPLATE = 'http://www.google.com/search?q=%query%&tbs=dfn:1';
var THE_FREE_DICT_LINK_TEMPLATE = 'http://www.tfd.com/p/%query%';
var SPEAKER_ICON_URL = '/resources/images/img/speaker.png';
var HANDLE_ICON_URL = '/resources/images/img/handle.png';
var BACK_ICON_URL = '/resources/images/img/back.png';
var LOADER_ICON_URL = '/resources/images/img/loader.gif';
var EXTERNAL_ICON_URL = '/resources/images/img/external.png';
var GRADIENT_DOWN_URL = '/resources/images/img/gradient_down.png';
var GRADIENT_UP_URL = '/resources/images/img/gradient_up.png';
// Regexes.
var DICT_LINK_REGEX = /^http:\/\/en\.wiktionary\.org\/wiki\/([^:]*)$/;
var TITLE_CLASS_REGEX = RegExp('(^|\s)' +  ROOT_ID + '_title(\s|$)')

// Internal global vars.
var body = document.getElementsByTagName('body')[0];
var breadcrumbs = [];
var last_query = null;
var audio_cache = {};

// Extension options with defaults.
var options = {
  clickModifier: 'Ctrl',
  shortcutModifier: 'Ctrl',
  shortcutKey: 'Q',
  shortcutEnable: true,
  shortcutSelection: false,
  frameWidth: 550,
  frameHeight: 250,
  queryFormWidth: 250,
  queryFormHeight: 50,  // This one is an approximation for centering.
  hideWithEscape: true,
  saveFrameSize: true,
  showExamples: true,
  showPOS: false,
  showLabels: true,
  showIPA: true,
  showAudio: true,
  showAudioLinks: true,
  showRelated: false,
  showSynonyms: true,
  showAntonyms: false,
  showLinks: false,
  showEtymology: false
}

/***************************************************************
 *                          Entry Point                        *
 ***************************************************************/
// Main initialization function. Loads options and sets listeners.
function initialize() {
  // Load options.
  function setOpt(opt) {
    backgroundFunctions({method: "retrieve", arg: opt}, function(response) {
      if (response != null) options[opt] = response;
    });
  }

  for (var opt in options) {
    setOpt(opt);
  }

  // Manually inject the stylesheet into non-HTML pages that have no <head>.
  if (!document.head && document.body) {
    link = document.createElement('link');
    link.rel = 'stylesheet';
    link.type = 'text/css';
    link.href = 'frame.css';

    document.body.appendChild(link);
  }

  // Override label visibility.
  if (!options.showLabels) {
    label_style = document.createElement('style');
    label_style.type = 'text/css';
    label_style.innerText = '#chrome_ggl_dict_ext .label {display: none !important}';
    (document.head || document.body).appendChild(label_style);
  }

  // Set event listeners.
  window.addEventListener('keydown', handleKeypress, false);
  setTimeout(function() {
    if (options.clickModifier == 'None') {
      window.addEventListener('mousedown', function(e) {
        if (!isClickInsideFrame(e)) removePopup(true, true);
      }, false);
      window.addEventListener('dblclick', handleClick, false);
    } else {
      window.addEventListener('mouseup', handleClick, false);
    }
  }, 100);
}

/***************************************************************
 *                        Event Handlers                       *
 ***************************************************************/
// Handle lookup-on-click.
function handleClick(e) {
  is_inside_frame = isClickInsideFrame(e);

  // If click outside the frame/form, remove it.
  if (!is_inside_frame) removePopup(true, true);

  // If the modifier is held down and we have a selection, create a pop-up.
  if (checkModifier(options.clickModifier, e)) {
    var query = getTrimmedSelection();
    if (query != '') {
      if (is_inside_frame) {
        if (last_query) breadcrumbs.push(last_query);
        navigateFrame(query);
      } else {
        breadcrumbs = [];
        createPopup(query, e.pageX, e.pageY, e.clientX, e.clientY, false);
      }
      e.preventDefault();
      getSelection().removeAllRanges();
    }
  }
}

// Handle keyboard shortcut.
function handleKeypress(e) {
  if (options.hideWithEscape && e.keyCode == 27) {
    removePopup(true, true);
    return;
  }

  if (!options.shortcutEnable) return;
  if (!checkModifier(options.shortcutModifier, e)) return;
  if (options.shortcutKey.charCodeAt(0) != e.keyCode) return;

  if (options.shortcutSelection && getTrimmedSelection() != '') {
    // Lookup selection.
    removePopup(true, true);
    breadcrumbs = [];
    createCenteredPopup(getTrimmedSelection());
  } else {
    // Show query form if it's not already visible or clear it otherwise.
    if (!document.getElementById(FORM_ID)) {
      removePopup(true, false);
      grayOut(true);
      createQueryForm();
    } else {
      document.getElementById(FORM_ID).getElementsByTagName('input')[0].value = '';
    }
  }
}

// Handle clicks on related terms.
function navigateFrame(query) {
  var frame_ref = document.getElementById(ROOT_ID);
  var fixed = (document.defaultView.getComputedStyle(frame_ref, null).getPropertyValue('position') == 'fixed');
  var zoom_ratio = getZoomRatio();
  createPopup(query,
              frame_ref.offsetLeft * zoom_ratio, frame_ref.offsetTop * zoom_ratio,
              frame_ref.offsetLeft * zoom_ratio - body.scrollLeft, frame_ref.offsetTop * zoom_ratio - body.scrollTop,
              fixed);
}

/***************************************************************
 *                        UI Controllers                       *
 ***************************************************************/
// Creates and shows the manual query form.
function createQueryForm() {
  // Calculate the coordinates of the middle of the window.
  var windowX = (window.innerWidth - (PADDING_LEFT + options.queryFormWidth + PADDING_RIGHT)) / 2 ;
  var windowY = (window.innerHeight - (PADDING_TOP + options.queryFormHeight + PADDING_BOTTOM)) / 2;
  var x = body.scrollLeft + windowX;
  var y = body.scrollTop + windowY;

  // Create the form, set its id and insert it.
  var qform = document.createElement('div');
  qform.id = FORM_ID;
  body.appendChild(qform);

  // Set form style.
  var zoom_ratio = getZoomRatio();
  qform.style.position = 'absolute';
  qform.style.left = (x / zoom_ratio) + 'px';
  qform.style.top = (y / zoom_ratio) + 'px';
  qform.style.width = options.queryFormWidth + 'px';
  qform.style.zIndex = BASE_Z_INDEX;

  // Add textbox.
  textbox = document.createElement('input');
  textbox.type = 'text';
  qform.appendChild(textbox);

  function initLookup() {
    grayOut(false);
    removePopup(false, true);
    if (textbox.value.replace(/^\s+|\s+$/g, '') != '') {
      breadcrumbs = [];
      createCenteredPopup(textbox.value);
    }
  }

  textbox.focus();

  // Add button.
  button = document.createElement('input');
  button.type = 'button';
  button.value = 'Lookup';
  qform.appendChild(button);

  // Set lookup event handlers.
  textbox.addEventListener('keypress', function(e) {
    if (e.keyCode == 13) {  // Pressed Enter.
      setTimeout(initLookup, 400);
    }
  }, false);

  button.addEventListener('click', function(e) {
    setTimeout(initLookup, 400);
  }, false);

  // Schedule a resize of the textbox to accommodate the button in a single line.
  setTimeout(function() {
    var width = options.queryFormWidth - button.offsetWidth - 2 * PADDING_FORM - 3;
    textbox.style.width = width + 'px';
  }, 100);

  // Initiate the fade-in animation in after 100 milliseconds.
  // Setting it now will not trigger the CSS3 animation sequence.
  setTimeout(function() {
    qform.style.opacity = 1;
  }, 100);
}

// Create a centered pop-up.
function createCenteredPopup(query) {
  var windowX = (window.innerWidth - (PADDING_LEFT + options.frameWidth + PADDING_RIGHT)) / 2;
  var windowY = (window.innerHeight - (PADDING_TOP + options.frameHeight + PADDING_BOTTOM)) / 2;

  // Create new popup.
  createPopup(query, windowX, windowY, windowX, windowY, true);
}

// Create and fade in the dictionary popup frame and button.
function createPopup(query, x, y, windowX, windowY, fixed) {
  // If an old frame still exists, wait until it is killed.
  var frame_ref = document.getElementById(ROOT_ID);
  if (frame_ref) {
    if (frame_ref.style.opacity == 1) removePopup(true, false);
    setTimeout(function() {createPopup(query, x, y, windowX, windowY, fixed);}, 50);
    return;
  }

  // Create the frame, set its id and insert it.
  var frame = document.createElement('div');
  frame.id = ROOT_ID;
  // Unique class to differentiate between frame instances.
  frame.className = ROOT_ID + (new Date()).getTime();
  
  //for some reason document does not have body element
  //in script initialization, so we set it again here
  if (! body){
	  body = document.getElementsByTagName('body')[0];
  }
  body.appendChild(frame);

  // Make frame draggable by its top.
  makeMoveable(frame, PADDING_TOP);

  // Start loading frame data.
  backgroundFunctions({method: 'lookup', arg: query}, function(response) {
    if (response != null) {
      frame.innerHTML = createHtmlFromLookup(query, response);
      
      // Add some dynamic element after the HTML is loaded.
      setTimeout(function() {
        // Create a dragging handle.
        var handle = document.createElement('div');
        handle.id = ROOT_ID + '_handle';
        frame.appendChild(handle);
        handle.style.background = 'url("' + HANDLE_ICON_URL + '") !important';

        makeResizeable(frame, handle);

        // Create back link.
        if (breadcrumbs.length) {
          var back = document.createElement('div');
          back.id = ROOT_ID + '_back';
          frame.appendChild(back);
          back.style.background = 'url("' + BACK_ICON_URL + '") !important';

          back.addEventListener('click', function() {
            navigateFrame(breadcrumbs.pop());
          });
        }

        // Resize shaders to avoid shading scroll bar arrows.
        var shader_top = document.getElementById(ROOT_ID + '_shader_top');
        var shader_bottom = document.getElementById(ROOT_ID + '_shader_bottom');
        if (shader_top && shader_bottom) {
          shader_top.style.width = (shader_top.offsetWidth - 16) + 'px';
          shader_bottom.style.width = (shader_bottom.offsetWidth - 16) + 'px';
        }

        // Hook into dictionary links.
        var links = frame.getElementsByTagName('a');
        for (var i in links) {
          if (links[i].href && DICT_LINK_REGEX.test(links[i].href) && !TITLE_CLASS_REGEX.test(links[i].className)) {
            links[i].addEventListener('click', function(e) {
              if (e.which == 1) {
                var link_word = this.href.match(DICT_LINK_REGEX)[1];
                if (last_query) breadcrumbs.push(last_query);
                navigateFrame(unescape(link_word));
                e.preventDefault();
              }
            });
          }
        }

        // Hook into audio icons.
        var spans = frame.getElementsByTagName('span');
        for (var i in spans) {
          if (spans[i].className == ROOT_ID + '_audio') {
            registerAudioIcon(spans[i].getElementsByTagName('img')[0],
                              spans[i].attributes['data-src'].value);
          }
        }
      }, 100);
    }
  });

  // Calculate frame position.
  var window_width = window.innerWidth;
  var window_height = window.innerHeight;
  var full_frame_width = PADDING_LEFT + options.frameWidth + PADDING_RIGHT;
  var full_frame_height = PADDING_TOP + options.frameHeight + PADDING_BOTTOM;
  var top = 0;
  var left = 0;
  var zoom_ratio = getZoomRatio();

  if (windowX + full_frame_width * zoom_ratio >= window_width) {
    left = x / zoom_ratio - full_frame_width;
    if (left < 0) left = 5;
  } else {
    left = x / zoom_ratio;
  }

  if (windowY + full_frame_height * zoom_ratio >= window_height) {
    top = y / zoom_ratio - full_frame_height;
    if (top < 0) top = 5;
  } else {
    top = y / zoom_ratio;
  }

  // Set frame style.
  frame.style.position = fixed ? 'fixed' : 'absolute';
  frame.style.left = left + 'px';
  frame.style.top = top + 'px';
  frame.style.width = options.frameWidth + 'px';
  frame.style.height = options.frameHeight + 'px';
  frame.style.zIndex = BASE_Z_INDEX;
  frame.style.background = 'white url("' + LOADER_ICON_URL + '") center no-repeat !important';

  // Initiate the fade-in animation in after 100 milliseconds.
  // Setting it now will not trigger the CSS3 animation sequence.
  setTimeout(function() {
    frame.style.opacity = 1;
  }, 100);

  last_query = query;
}

function registerAudioIcon(icon, filename) {
  function playAudio(url, src_element) {
    new Audio(url).addEventListener('canplaythrough', function() {
      this.play();
      src_element.style.cursor = 'auto';
    });
  }

  icon.addEventListener('click', function(e) {
    var src_element = this;
    src_element.style.cursor = 'progress';
    if (audio_cache[filename]) {
      playAudio(audio_cache[filename], src_element);
    } else {
      backgroundFunctions({method:'get_audio', arg: filename}, function(url) {
        audio_cache[filename] = url;
        playAudio(url, src_element);
      });
    }
  });
}

// Fade out then destroy the frame and/or form.
function removePopup(do_frame, do_form) {
  var form = document.getElementById(FORM_ID);

  if (form && do_form) {
    grayOut(false);
    form.style.opacity = 0;
    setTimeout(function() {if (form) body.removeChild(form);}, 400);
  }

  // Remember the current frame's unique class name.
  var frame_ref = document.getElementById(ROOT_ID);
  var frame_class = frame_ref ? frame_ref.className : null;

  if (frame_ref && do_frame) {
    frame_ref.style.opacity = 0;
    setTimeout(function() {
      var frame_ref = document.getElementById(ROOT_ID);
      // Check if the currently displayed frame is still the same as the old one.
      if (frame_ref && frame_ref.className == frame_class) {
        body.removeChild(frame_ref);
      }
    }, 400);
  }
}

function createHtmlFromLookup(query, dict_entry) {
	this.$tempDiv = $('<div></div>')
	.hide()
	.append(dict_entry);

	pinYin = this.$tempDiv.find('span[class*="pinyin"]').find("a").attr("title");
	zhuYin = this.$tempDiv.find("span[class='Bopo']").text();
	definition = this.$tempDiv.find("ol")[0].innerHTML;
	return query + "<br>" + pinYin + " " + zhuYin + ' ' + definition; 
}

/***************************************************************
 *                   General Helper Functions                  *
 ***************************************************************/
// Background graying function, based on: 
// http://www.hunlock.com/blogs/Snippets:_Howto_Grey-Out_The_Screen
function grayOut(vis) {
  // Pass true to gray out screen, false to ungray.
  var dark_id = ROOT_ID + '_shader';
  var dark = document.getElementById(dark_id);
  var first_time = (dark == null);

  if (first_time) {
    // First time - create shading layer.
    var tnode = document.createElement('div');
    tnode.id = dark_id;

    tnode.style.position = 'absolute';
    tnode.style.top = '0px';
    tnode.style.left = '0px';
    tnode.style.overflow = 'hidden';

    document.body.appendChild(tnode);
    dark = document.getElementById(dark_id);
  }

  if (vis) {
    // Set the shader to cover the entire page and make it visible.
    dark.style.zIndex = BASE_Z_INDEX - 1;
    dark.style.backgroundColor = '#000000';
    dark.style.width = body.scrollWidth + 'px';
    dark.style.height = body.scrollHeight + 'px';
    dark.style.display = 'block';

    setTimeout(function() {dark.style.opacity = 0.7;}, 100);
  } else if (dark.style.opacity != 0) {
    setTimeout(function() {dark.style.opacity = 0;}, 100);
    setTimeout(function() {dark.style.display = 'none';}, 400);
  }
}

// Returns a trimmed version of the currently selected text.
function getTrimmedSelection() {
  var selection = String(window.getSelection());
  return selection.replace(/^\s+|\s+$/g, '');
}

// Returns the document body's zoom ratio.
function getZoomRatio() {
  var zoom_ratio = document.defaultView.getComputedStyle(body, null).getPropertyValue('zoom');
  return parseFloat(zoom_ratio || '0');
}

// Predicate to check whether the selected modifier key is active in an event.
function checkModifier(modifier, e) {
  switch (modifier) {
    case 'None':
      return true;
    case 'Ctrl':
      return e.ctrlKey;
    case 'Alt':
      return e.altKey;
    case 'Meta':
      return e.metaKey;
    case 'Ctrl+Alt':
      return e.ctrlKey && e.altKey;
    case 'Ctrl+Shift':
      return e.ctrlKey && e.shiftKey;
    case 'Alt+Shift':
      return e.altKey && e.shiftKey;
    default:
      return false;
  }
}

// Makes a container resizeable through dragging a handle.
function makeResizeable(container, handle) {
  var last_position = {x: 0, y: 0};
  var ruler = document.createElement('div');
  ruler.style.visibility = 'none';
  ruler.style.width = '100px';

  function moveListener(e) {
    var moved = {x: (e.clientX - last_position.x),
                 y: (e.clientY - last_position.y)};

    var zoom_ratio = parseFloat(document.defaultView.getComputedStyle(ruler, null).getPropertyValue('width')) / 100;;
    var height = parseFloat(document.defaultView.getComputedStyle(container, null).getPropertyValue('height'));
    var width = parseFloat(document.defaultView.getComputedStyle(container, null).getPropertyValue('width'));
    var new_height = (height + moved.y) / zoom_ratio;
    var new_width = (width + moved.x) / zoom_ratio;

    if (moved.y > 0 || height >= 100) {
      last_position.y = e.clientY;
      container.style.height = new_height + 'px';
      content_box = document.getElementById(ROOT_ID + '_content');
      content_box.style.height = new_height + 'px';
      if (options.saveFrameSize) {
        options.frameHeight = new_height;
        backgroundFunctions('store', 'frameHeight', new_height, function(response) {});
      }
    }
    if (moved.x > 0 || width >= 250) {
      last_position.x = e.clientX;
      container.style.width = new_width + 'px';
      shader_top = document.getElementById(ROOT_ID + '_shader_top');
      shader_bottom = document.getElementById(ROOT_ID + '_shader_bottom');
      shader_top.style.width = (shader_top.offsetWidth + moved.x / zoom_ratio) + 'px';
      shader_bottom.style.width = (shader_bottom.offsetWidth + moved.x / zoom_ratio) + 'px';

      if (options.saveFrameSize) {
        options.frameWidth = new_width;
        backgroundFunctions({method: 'store', arg: 'frameWidth', arg2: new_width}, function(response) {});
      }
    }

    e.preventDefault();
  }

  handle.addEventListener('mousedown', function(e) {
    last_position = {x: e.clientX, y: e.clientY};
    window.addEventListener('mousemove', moveListener);
    body.appendChild(ruler);
    window.addEventListener('mouseup', function(e) {
      window.removeEventListener('mousemove', moveListener);
      try {
        body.removeChild(ruler);
      } catch (e) {}
      e.preventDefault();
    });
    e.preventDefault();
  });
}

// Makes a box moveable by dragging its top margin.
function makeMoveable(box, margin) {
  var last_position = {x: 0, y: 0};

  var dragger = document.createElement('div');
  dragger.id = ROOT_ID + '_dragger';
  dragger.style.height = margin + 'px !important';
  box.appendChild(dragger);

  function moveListener(e) {
    var moved = {x: (e.clientX - last_position.x),
                 y: (e.clientY - last_position.y)};
    last_position = {x: e.clientX, y: e.clientY};
    box.style.top = (box.offsetTop + moved.y) + 'px';
    box.style.left = (box.offsetLeft + moved.x) + 'px';

    e.preventDefault();
  }

  dragger.addEventListener('mousedown', function(e) {
    last_position = {x: e.clientX, y: e.clientY};
    window.addEventListener('mousemove', moveListener);
    window.addEventListener('mouseup', function(e) {
      window.removeEventListener('mousemove', moveListener);
      e.preventDefault();
    });
    e.preventDefault();
  });
}

function isClickInsideFrame(e) {
  frame_ref = document.getElementById(ROOT_ID);
  if (frame_ref) {
    var x, y;
    if (frame_ref.style.position == 'absolute') {
      x = e.pageX;
      y = e.pageY;
    } else if (frame_ref.style.position == 'fixed') {
      x = e.clientX;
      y = e.clientY;
    }

    var zoom_ratio = getZoomRatio();
    x /= zoom_ratio;
    y /= zoom_ratio;

    if (x >= frame_ref.offsetLeft &&
        x <= frame_ref.offsetLeft + frame_ref.offsetWidth &&
        y >= frame_ref.offsetTop &&
        y <= frame_ref.offsetTop + frame_ref.offsetHeight) {
      return true;
    }
  }

  return false;
}

/******************************************************************************/
initialize();