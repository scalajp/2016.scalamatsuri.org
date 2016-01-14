var scalamatsuri = scalamatsuri || {};

scalamatsuri.displayDescription = function(id, raw) {
  var w = $(window).width() - 20;
  var h = $(window).height() - 20;
  if (w < 0) {
    w = 10;
  }
  if (h < 0) {
    h = 10;
  }
  if (w > 600) {
    w = 600;
  }
  if (h > 400) {
    h = 400;
  }
  $("#dialog_message #dialog_contents").remove();
  $("#dialog_message").append(
    $("<div id='dialog_contents'/>").append(raw)
  ).dialog({
    modal: true,
    width: w,
    height: h,
    buttons: {
      Ok: function() {
        $(this).dialog("close");
      }
    }
  });
}

$(document).ready(function(){
  $(".gnavSmClick").click(function(){
    $(".smVerGnav").slideToggle();
  });

  $(".btn1space").click(function(){
    resetTable();
    $(".roomA").show();
    $(this).addClass("onNavLink");
  });

  $(".btn2space").click(function(){
    resetTable();
    $(".roomB").show();
    $(this).addClass("onNavLink");
  });

  $(".btn3space").click(function(){
    resetTable();
    $(".roomC").show();
    $(this).addClass("onNavLink");
  });

  function resetTable(){
    $(".btn1space").removeClass("onNavLink");
    $(".btn2space").removeClass("onNavLink");
    $(".btn3space").removeClass("onNavLink");
    $(".roomA").hide();
    $(".roomB").hide();
    $(".roomC").hide();
  }

  function initSchedule(){
    if ($(document).width() >= 1024) {
      $('.day1roomswitch').hide();
      $('.day2roomswitch').hide();
      $(".roomA").show();
      $(".roomB").show();
      $(".roomC").show();
    } else {
      resetTable();
      $('.day1roomswitch').show();
      $('.day2roomswitch').show();
      $(".btn1space").addClass("onNavLink");
      $(".roomA").show();
    }
  }
  var lastWidth = $(window).width();
  $(window).resize(function() {
    var w = $(window).width();
    // scrolling causes resize events to occur on smart phones
    if (lastWidth !== w) {
      lastWidth = w;
      initSchedule();
    }
  });
  initSchedule();
});
