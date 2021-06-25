<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
<h2>등록 페이지</h2>
<sf:form action="register" method="post" modelAttribute="postDto">
    <label for="inputTitle">제목 : </label>
    <sf:input path="title" id="inputTitle" type="text" style="width:420px" /><br>
    <div id="errorTitle" style="color:red"></div><br>
    <label for="inputContent">내용 : </label>
    <sf:textarea path="content" id="inputContent" rows="4" cols="50" /><br>
    <div id="errorContent" style="color:red"></div>
    <br><button type="submit" id="registerButton">등록</button>
    <button><a href="${pageContext.request.contextPath}/search">취소</a></button>
</sf:form>
</body>
<script>
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
</script>
</html>
