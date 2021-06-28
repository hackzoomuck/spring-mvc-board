$(document).ready(function () {
const $inputTitle = $("#inputTitle");
const $errorTitle = $("#errorTitle");
const $inputContent = $("#inputContent");
const $errorContent = $("#errorContent");

$inputTitle.on("input", function () {
  $errorTitle.text("");
  let inputTitleValue = $inputTitle.val();
  if(inputTitleValue[0] === " ") {
  $errorTitle.text("제목 맨 앞부분은 공백 없이 입력해주세요.")
  }
  if(inputTitleValue.length > 20) {
  $errorTitle.text("제목은 20글자 이하 입력 가능합니다.")
  $inputTitle.val(inputTitleValue.substring(0, 20));
  }
})

$inputContent.on("input", function () {
  $errorContent.text("");
  let inputContentValue = $inputContent.val();
  if(inputContentValue.length > 100) {
  $errorContent.text("내용은 100글자 이하 입력 가능합니다.")
  $inputContent.val(inputContentValue.substring(0,100));
}
})

$("#registerButton").on("click", function (event) {
  let inputTitleValue = $inputTitle.val();
  let inputContentValue = $inputContent.val();
  if(inputTitleValue.length === 0) {
  $errorTitle.text("제목을 입력해주세요.")
  event.preventDefault();
  }
  if(inputContentValue.length === 0) {
  $errorContent.text("내용을 입력해주세요.")
  event.preventDefault();
  }
})
})
