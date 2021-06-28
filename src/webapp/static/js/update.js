$(document).ready(function () {
  const $inputTitle = $("#inputTitle");
  const $inputContent = $("#inputContent");
  const $errorTitle = $("#errorTitle");
  const $errorContent = $("#errorContent");
  const preInputTitle = $inputTitle.val();
  const preInputContent = $inputContent.val();

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

  $("#updateButton").on("click", function (event){
    if (preInputTitle !== $inputTitle.val()){
      let inputTitleValue = $inputTitle.val();
      if(inputTitleValue.length === 0) {
        $errorTitle.text("제목을 입력해주세요.")
        event.preventDefault();
        }
    }
    if (preInputContent !== $inputContent.val()) {
      let inputContentValue = $inputContent.val();
      if(inputContentValue.length === 0) {
        $errorContent.text("내용을 입력해주세요.")
        event.preventDefault();
      }
    }
    if (preInputTitle === $inputTitle.val() && preInputContent === $inputContent.val()){
      alert("변경된 내용이 없습니다.")
      event.preventDefault();
    }
  })
  $("#cancelButton").on("click", function (){
    const url = window.location.origin + "/detail/" + $(this).val();
    window.location.assign(url);
  })
})
