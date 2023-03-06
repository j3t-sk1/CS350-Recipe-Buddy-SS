// Timer function:
let hour = 0;
let minute = 0;
let second = 0;
let millisecond = 0;

let cron;

document.form_main.start.onclick = () => start();
document.form_main.pause.onclick = () => pause();
document.form_main.reset.onclick = () => reset();

function start() {
  pause();
  cron = setInterval(() => { timer(); }, 10);
}

function pause() {
  clearInterval(cron);
}

function reset() {
  hour = 0;
  minute = 0;
  second = 0;
  millisecond = 0;
  document.getElementById('hour').innerText = '00';
  document.getElementById('minute').innerText = '00';
  document.getElementById('second').innerText = '00';
  document.getElementById('millisecond').innerText = '000';
}

function timer() {
  if ((millisecond += 10) == 1000) {
    millisecond = 0;
    second++;
  }
  if (second == 60) {
    second = 0;
    minute++;
  }
  if (minute == 60) {
    minute = 0;
    hour++;
  }
  document.getElementById('hour').innerText = returnData(hour);
  document.getElementById('minute').innerText = returnData(minute);
  document.getElementById('second').innerText = returnData(second);
  document.getElementById('millisecond').innerText = returnData(millisecond);
}

function returnData(input) {
  return input >= 10 ? input : `0${input}`
}
// -- End of timer function


// var modal = document.querySelector(".modal");
// var container = modal.querySelector(".container");

// document.querySelector("button").addEventListener("click", function (e) {
//   modal.classList.remove("hidden")
// });

// document.querySelector(".modal").addEventListener("click", function (e) {
//   if (e.target !== modal && e.target !== container) return;     
//   modal.classList.add("hidden");
// });



const modal = document.querySelector(".modal");
const trigger = document.querySelector(".trigger");
const closeButton = document.querySelector(".close-button");

function toggleModal() {
    modal.classList.toggle("show-modal");
}

function windowOnClick(event) {
    if (event.target === modal) {
        toggleModal();
    }
}

trigger.addEventListener("click", toggleModal);
closeButton.addEventListener("click", toggleModal);
window.addEventListener("click", windowOnClick); 


//Dropdown menu

if(typeof String.prototype.trim !== 'function') {
    
  String.prototype.trim = function()
  {
      return this.replace(/^\s+|\s+$/g, '');
  }
}

var checkbox_select = function(params)
{
  // Error handling

  var error = false;

  if(!params.selector) {                                              console.error("selector needs to be defined"); error = true; }

  if(typeof params.selector != "string") {                            console.error("selector needs to be a string"); error = true; }

  if(!$(params.selector).is("form")) {                                console.error("Element needs to be a form"); error = true; }

  if($(params.selector).find("select").length < 1) {                  console.error("Element needs to have a select in it"); error = true; }

  if($(params.selector).find("option").length < 1) {                  console.error("Select element needs to have an option in it"); error = true; }

  if($(params.selector).find('select').attr('name') == undefined) {   console.error("Select element needs to have a name attribute"); error = true; }

  if(error)
      return false;

  var that            = this,
      $_native_form   = $(params.selector),
      $_native_select = $_native_form.find('select'),
      
      selector                = params.selector,
      select_name             = $_native_select.attr('name').charAt(0).toUpperCase() + $_native_select.attr('name').substr(1),
      selected_translation    = params.selected_translation   ? params.selected_translation   : "selected",
      all_translation         = params.all_translation        ? params.all_translation        : "All " + select_name + "s",
      not_found_text          = params.not_found              ? params.not_found              : "No " + select_name + "s found.",
      currently_selected      = [],
      
      $_parent_div    = $("<div />")      .addClass("checkbox_select"),
      $_select_anchor = $("<a />")        .addClass("checkbox_select_anchor")     .text( select_name ),
      $_search        = $("<input />")    .addClass("checkbox_select_search"),
      $_submit        = $("<input />")    .addClass("checkbox_select_submit")     .val("Apply") .attr('type','submit') .data("selected", ""),
      $_dropdown_div  = $("<div />")      .addClass("checkbox_select_dropdown"),
      $_not_found     = $("<span />")     .addClass("not_found hide")             .text(not_found_text),
      $_ul            = $("<ul />"),

      updateCurrentlySelected = function()
      {
          var selected = [];

          $_ul.find("input:checked").each(
                                                      
              function()
              {
                  selected.push($(this).val());
              }
          );

          currently_selected = selected;

          if(selected.length == 0)
          {
                  $_select_anchor.text( select_name )
          }
          else if(selected.length == 1)
          {
              $_select_anchor.text( selected[0] + " " + selected_translation );
          }
          else if(selected.length ==  $_ul.find("input[type=checkbox]").length)
          {
              $_select_anchor.text( all_translation );
          }
          else
          {
              $_select_anchor.text( selected.length + " " + selected_translation );
          }
      },

      createItem  = function(name, value, count)
      {
          var uID             = 'checkbox_select_' + select_name + "_" + name.replace(" ", "_"),
              $_li            = $("<li />"),
              $_checkbox      = $("<input />").attr(
                                      {
                                          'name'  : name,
                                          'id'    : uID,
                                          'type'  : "checkbox",
                                          'value' : value
                                      }
                                  )
                                  .click(

                                      function()
                                      {
                                          updateCurrentlySelected();
                                      }
                                  ),

              $_label         = $("<label />").attr('for', uID),
              $_name_span     = $("<span />").text(name).prependTo($_label),
              $_count_span    = $("<span />").text(count).appendTo($_label);
                      
          return $_li.append( $_checkbox.after( $_label ) );
      },
  
  apply = function()
  {
    $_dropdown_div.toggleClass("show");
    $_parent_div.toggleClass("expanded");
      
    if(!$_parent_div.hasClass("expanded"))
    {  
      if(currently_selected != $_submit.data("selected"))
      {
        $_submit.data("selected" , currently_selected);

        that.onApply(
          { 
            selected : $_submit.data("selected")
          }
        );
      }		
    }					
  };
  
  that.onApply = typeof params.onApply == "function" ? 
              
                  params.onApply :
              
                  function(e) 
                  {
                  };

  that.update = function() 
  {
      $_ul.empty();
      $_native_select.find("option").each(

          function(i)
          {
              $_ul.append( createItem( $(this).text(), $(this).val(), $(this).data("count") ) );
          }
      );

      updateCurrentlySelected();
  }

  that.check = function(checkbox_name, checked) 
  {

  $_ul.find("input[type=checkbox]").each(function()
  {
    if($(this).attr('name') == checkbox_name)
    {
      $(this).attr('checked', checked ? checked : false);
      return false;
    }
  });
  
      updateCurrentlySelected();

  }
  $_dropdown_div  .prepend($_search);
  $_dropdown_div  .append($_ul);
  $_dropdown_div  .append($_not_found);
  $_dropdown_div  .append($_submit);
  $_dropdown_div  .appendTo($_parent_div);
  $_select_anchor .prependTo($_parent_div);

  that.update();

  $_select_anchor.click( 

      function()
      {
          apply();
      }
  );
           
  $_search.keyup(

      function()
      {
          var search = $(this).val().toLowerCase().trim();

          if( search.length == 1 )
          {
              $_ul.find("label").each(

                  function()
                  {
                      if($(this).text().toLowerCase().charAt(0) == search.charAt(0))
                      {
                          if($(this).parent().hasClass("hide"))
                              $(this).parent().removeClass("hide");

                          if(!$_not_found.hasClass("hide"))
                              $_not_found.addClass("hide");
                      }
                      else
                      {
                          if(!$(this).parent().hasClass("hide"))
                              $(this).parent().addClass("hide");

                          if($_not_found.hasClass("hide"))
                              $_not_found.removeClass("hide");
                      }
                  }
              );
          }
          else
          {
              if($_ul.text().toLowerCase().indexOf(search) == -1)
              {
                  if($_not_found.hasClass("hide"))
                      $_not_found.removeClass("hide");
              }
              else
              {
                  if(!$_not_found.hasClass("hide"))
                      $_not_found.addClass("hide");
              }
                  
              $_ul.find("label").each(

                  function()
                  {
                      if($(this).text().toLowerCase().indexOf(search) > -1)
                      {
                          if($(this).parent().hasClass("hide"))
                              $(this).parent().removeClass("hide");
                      }
                      else
                      {
                          if(!$(this).parent().hasClass("hide"))
                              $(this).parent().addClass("hide");
                      }
                  }
              );
          }
      }
  );

  $_submit.click(
              
      function(e)
      {
          e.preventDefault();

          apply();
      }
  );

  $(params.selector).find('input[type=submit]').remove();

  $_native_select.after($_parent_div);

  $_native_select.hide();
};