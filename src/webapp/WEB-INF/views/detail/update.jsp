<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
<h2>수정 페이지</h2>
<sf:form action="" method="post" modelAttribute="postDto">
    <label for="inputTitle">제목 : </label>
    <sf:input path="title" id="inputTitle" type="text" style="width:420px"/><br>
    <div id="errorTitle" style="color:red"></div><br>
    <label for="inputContent">내용 : </label>
    <sf:textarea path="content" id="inputContent" type="text" rows="4" cols="50" /><br>
    <div id="errorContent" style="color:red"></div><br>
    <button type="submit" id="updateButton">수정</button>
    <button type="button" value="${postId}" id="cancelButton">취소</button>
</sf:form>
</body>
<script>
  $(document).ready(function () {
    const $inputTitle = $("#inputTitle");
    const $inputContent = $("#inputContent");
    const $errorTitle = $("#errorTitle");
    const $errorContent = $("#errorContent");
    const preInputTitle = $inputTitle.val();
    const preInputContent = $inputContent.val();

    debugger;

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
          event.preventDefault();
          $errorTitle.text("제목을 입력해주세요.")
        }
      }
      if (preInputContent !== $inputContent.val()) {
        let inputContentValue = $inputContent.val();
        if(inputContentValue.length === 0) {
          $errorContent.text("내용을 입력해주세요.")
        }
      }
    })

    $("#cancelButton").on("click", function (){
      const url = window.location.origin + "/detail/" + $(this).val();
      window.location.assign(url);
    })
  })
</script>
</html>
