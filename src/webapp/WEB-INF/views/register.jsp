<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
<h2>등록 페이지</h2>
<form action="register" method="post">
    <label for="inputTitle">제목 : </label>
    <input id="inputTitle" type="text" required minlength="1" style="width:420px"></br>
    <div id="errorTitle" style="color:red"></div></br>
    <label for="inputContent">내용 : </label>
    <textarea id="inputContent" required minlength="1" rows="4" cols="50"></textarea></br>
    <div id="errorContent" style="color:red"></div>
    <br><button type="submit" id="registerButton">등록하기</button>
</form>
</body>
<script>
  $(document).ready(function () {
    const $errorTitle = $("#errorTitle");
    const $inputTitle = $("#inputTitle");
    const $inputContent = $("#inputContent");
    const $errorContent = $("#errorContent");

    $("#inputTitle").on("input", function () {
      $errorTitle.text("");
      let inputTitleValue = $inputTitle.val();
      if(inputTitleValue[0] === " ") {
        $errorTitle.text("제목 맨 앞부분은 공백 없이 입력해주세요.")
      }
      if(inputTitleValue.length > 20) {
        $errorTitle.text("제목은 20글자 이하 입력 가능합니다.")
        $("#inputTitle").val(inputTitleValue.substring(0, 20));
      }
    })

    $("#inputContent").on("input", function () {
      $errorContent.text("");
      let inputContentValue = $inputContent.val();
      if(inputContentValue.length > 100) {
        $errorContent.text("내용은 100글자 이하 입력 가능합니다.")
        $("#inputContent").val(inputContentValue.substring(0,100));
      }
    })

    $("#registerButton").on("click", function () {
      let inputTitleValue = $("#inputTitle").val();
      let inputContentValue = $("#inputContent").val();
      if(inputTitleValue.length == 0) {
        $errorTitle.text("제목을 입력해주세요.")
      }
      if(inputContentValue.length == 0) {
        $errorContent.text("내용을 입력해주세요.")
      }
    })
  })
</script>
</html>
