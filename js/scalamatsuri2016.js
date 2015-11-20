var scalamatsuri = scalamatsuri || {};

scalamatsuri.displayDescription = function(id) {
  $("#dialog_message iframe").remove();
  $("#dialog_message").append(
    $("<iframe />").attr("src", "/" + lang + "/candidates/" + id + "/").addClass("schedule_desc")
  ).dialog({
    modal: true,
    width: 800,
    height: 600,
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
    var w = $(window).window();
    // scrolling causes resize events to occur on smart phones
    if (lastWidth !== w) {
      lastWidth = w;
      initSchedule();
    }
  });
  initSchedule();
});
