// // Timer function:
// let hour = 0;
// let minute = 0;
// let second = 0;
// let millisecond = 0;

// let cron;
document.form_main.start.onclick = () => start();
document.form_main.pause.onclick = () => pause();
document.form_main.reset.onclick = () => reset();

// function start() {
//   pause();
//   cron = setInterval(() => { timer(); }, 10);
// }

// function pause() {
//   clearInterval(cron);
// }

// function reset() {
//   hour = 0;
//   minute = 0;
//   second = 0;
//   millisecond = 0;
//   document.getElementById('hour').innerText = '00';
//   document.getElementById('minute').innerText = '00';
//   document.getElementById('second').innerText = '00';
//   document.getElementById('millisecond').innerText = '000';
// }

// function timer() {
//   if ((millisecond += 10) == 1000) {
//     millisecond = 0;
//     second++;
//   }
//   if (second == 60) {
//     second = 0;
//     minute++;
//   }
//   if (minute == 60) {
//     minute = 0;
//     hour++;
//   }
//   document.getElementById('hour').innerText = returnData(hour);
//   document.getElementById('minute').innerText = returnData(minute);
//   document.getElementById('second').innerText = returnData(second);
//   document.getElementById('millisecond').innerText = returnData(millisecond);
// }

// function returnData(input) {
//   return input >= 10 ? input : `0${input}`
// }
 // -- End of timer function



 const modal = document.querySelector(".modal");
const trigger = document.querySelector(".trigger");
const trigger2 = document.querySelector(".trigger2");
const trigger3 = document.querySelector(".trigger3");
const trigger4 = document.querySelector(".trigger4");
const trigger5 = document.querySelector(".trigger5");
const closeButton = document.querySelector(".close-button");
const close2 = document.querySelector(".m_button_2")

function toggleModal() {
    modal.classList.toggle("show-modal");
}

function windowOnClick(event) {
  if (event.target === modal) {
    toggleModal();
  }
}

trigger.addEventListener("click", toggleModal);
trigger2.addEventListener("click", toggleModal);
trigger3.addEventListener("click", toggleModal);
trigger4.addEventListener("click", toggleModal);
trigger5.addEventListener("click", toggleModal);
closeButton.addEventListener("click", toggleModal);
close2.addEventListener("click", toggleModal);
window.addEventListener("click", windowOnClick); 



//dropdown
var options = [];

$( '.dropdown-menu a' ).on( 'click', function( event ) {

   var $target = $( event.currentTarget ),
       val = $target.attr( 'data-value' ),
       $inp = $target.find( 'input' ),
       idx;

   if ( ( idx = options.indexOf( val ) ) > -1 ) {
     options.splice( idx, 1 );
     setTimeout( function() { $inp.prop( 'checked', false ) }, 0);
    } else {
      options.push( val );
      setTimeout( function() { $inp.prop( 'checked', true ) }, 0);
  }

   $( event.target ).blur();

   console.log( options );
   return false;
});

var hours, minutes, seconds, countdown;

function timer() {
  // Get the user input
  hours = document.getElementById("hours").value;
  minutes = document.getElementById("minutes").value;
  seconds = document.getElementById("seconds").value;

  // Convert user input to seconds
  var totalSeconds = (parseInt(hours) * 60 * 60) + (parseInt(minutes) * 60) + parseInt(seconds);

  // Set the timer
  countdown = setInterval(function () {
    // Calculate hours, minutes, and seconds
    var hoursLeft = Math.floor(totalSeconds / 3600);
    var minutesLeft = Math.floor((totalSeconds - (hoursLeft * 3600)) / 60);
    var secondsLeft = totalSeconds - (hoursLeft * 3600) - (minutesLeft * 60);

    // Display the countdown
    document.getElementById("timer").innerHTML = hoursLeft + ":" + minutesLeft + ":" + secondsLeft;

    // Stop the timer when it reaches zero
    if (totalSeconds == 0) {
      clearInterval(countdown);
    } else {
      totalSeconds--;
    }
  }, 1000);
}

function stopCountdown() {
  clearInterval(countdown);
}

$('.table #deleteButton').on('click', function(event){
  event.preventDefault();
  var href = $(this).attr('href');
  $('#deleteModal #delRef').attr('href', href);
  $('deleteModal').modal();
})