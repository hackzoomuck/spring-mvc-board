# Spring 게시판

---

1. 제약 사항
    - maven, gradle 사용하지 않기
    - Spring boot 사용하지 않기

2. 목표
    - Spring 설정 (Spring 관련 라이브러리 다운 받아서 연결하기)
        - lib : spring-web 과 의존하는 것.
    - 게시판 CRUD 기능 구현
        - 등록
            - 제목, 내용, 저장버튼
        - 목록
            - 등록버튼, 검색(제목), 상세로 가는 링크, 페이징
        - 상세
            - 수정버튼, 삭제버튼
        - 삭제
        - 수정
            - 저장 버튼
    - view JSP

3. 편의 기능 :
    - JdbcTemplate (mysql)

4. 추가 :
    - 파일 업로드

---

### 1. Spring 설정

#### 1-1. library 추가

Maven Repository에서 Spring Web MVC에 필요한 라이브러리 Jar파일 다운. spring-mvc-board/lib에 Jar파일 저장.</br>
Intellij에 외부 라이브러리 사용방법.</br>
참고링크 : https://www.jetbrains.com/help/idea/library.html

```
Spring Web MVC » 5.3.7
Spring AOP » 5.3.7
Spring Beans » 5.3.7
Spring Context » 5.3.7
Spring Core » 5.3.7
Spring Web » 5.3.7
Spring Expression Language (SpEL) » 5.3.7
Spring Commons Logging Bridge » 5.3.7
```

#### 1-2 tomcat

- Preferences/Application Servers 에 ```Tomcat 10.0.6``` 추가
- application servers 설정
  </br>[참고링크] https://www.jetbrains.com/help/idea/application-servers-support.html
- Run/Debug configurations에 tomcat 서버 Local 등록 직후 tomcat을 실행하면 등록된 페이지가 없기에 404에러가 난다.

#### 1-3 스프링 MVC 설정

- DispacherServlet을 web.xml 파일에 설정
    - ```<servlet>```선언 :
    - ```<servlet-name>``` : DispacherServlet이 로드되면 서블릿 이름을 기반으로 명명된 XML 파일에서 스프링 애플리케이션 컨텍스트를 로드한다.
      여기서 서블릿 이름은 ```app```이므로 DispacherServlet이 애플리케이션의 WEB-INF 디렉터리에 위치한 ```app-servlet.xml```파일에서
      애플리케이션 컨텍스트 로드를 시도한다.
    - ```<servlet-mapping>``` : DispacherServlet이 처리할 URL을 지정함.

- app-servlet.xml 파일 작성
    - ```xmlns:mvc="http://www.springframework.org/schema/mvc"```
    - ```<mvc:resources mapping="/resources/**" location="/resource/" />``` : 정적 콘텐츠 제공을 위한 핸들러 설정.

#### 1-4 Project Structure

#### 1-5 MySQL

1. docker-compose.yml로 spring-mvc-board-mysql 컨테이너 생성. (이미지는 mysql:5.7 사용)
   [참고 링크] : https://hub.docker.com/_/mysql
    ```
    docker-compose up -d
    docker exec -it spring-mvc-board-mysql bash
    mysql --protocol=tcp -hlocalhost -P3306 -uroot -proot
    ```
2. board database에 board_table 생성
    ```
    create table post(
    title char(20) DEFAULT NULL, 
    content char(100) DEFAULT NULL, 
    post_id int PRIMARY KEY AUTO_INCREMENT);
    ```

3. Intellij와 mysql 연동.
   [참고 링크] : https://www.jetbrains.com/help/idea/connecting-to-a-database.html#connect-to-oracle-database

#### [error]

```[42000][1049] Unknown database 'spring-mvc-board-mysql'. . (44 ms)```
해결방법 : database설정에서 data source의 database를 spring-mvc-board mysql컨테이너의 사용할 database 이름으로 지정해줘야한다.

4. datasource 설정.

- DBCP BasicDataSource 사용.

```
Apache Commons DBCP » 2.8.0
Apache Commons Logging » 1.2
Apache Commons Pool » 2.8.1
```

#### 1-6 JDBC Template

NamedParameterJdbcTemplate class 사용.

```
Spring JDBC » 5.3.7
pring Transaction » 5.3.7
```

---

### 2. 게시판 CRUD 기능 구현

#### 2-1. 게시물 검색, 출력

1. select box에서 '전체, 게시물 번호, 제목, 내용' 선택한다.
2. 검색 키워드를 입력하고 '검색' 버튼을 누른다.
3. 검색된 게시물(게시물 번호, 제목, 내용)이 출력된다.
4. '전체검색' 버튼을 누르면 전체 게시물이 출력된다.

#### 2-2. 게시물 등록

1. 검색페이지에 등록버튼 추가.
2. 등록페이지 : 등록버튼 누른다.
    1. 입력 유효성 검사 :
        - client : 제목 첫글자 공백, 제목 20자 초과, 제목 빈칸, 내용 100자 초과, 내용 빈칸.
        - (x) server :
    2. DB에 저장 :
        - 한글 인코딩(web.xml : filter)
    3. 상세페이지 이동

#### 2-3. 게시물 상세내용

검색 : 게시물 <tr>을 클릭시 해당 게시물의 상세내용 페이지로 이동.<br>
수정 : 1) 수정버튼 : 수정된 내용으로 해당 게시물의 상세내용 페이지로 이동. 2) 취소버튼 : 내용 변경되지 않고 상세내용 페이지로 이동.<br>
등록 : 등록페이지에서 등록 후 등록된 게시물의 상세내용 페이지로 이동.(문제점 : 가장 최신에 저장된 내용을 가져오도록 되어 있음.)<br>

1. 목록 버튼 : localhost:8080/search 로 이동
2. 수정 버튼 : localhost:8080/detail/update/{postId}? 로 이동
3. 삭제 버튼 : 삭제 후 localhost:8080/search 로 이동

#### 2-4. 게시물 수정

상세페이지에서 수정버튼 클릭.

1. 제목, 내용 수정 :
    1) 제목, 내용 수정된 경우 : 등록과 같은 클라이언트 유효성 검사. 변경된 내용을 DB에 저장.
    2) 제목, 내용 수정되지 않은 경우 : 수정버튼 클릭시 '변경된 내용이 없습니다.' 알림창이 나타나고 다른 페이지로 이동 없음.

#### 2-5. 게시물 삭제

상세페이지에서 삭제버튼 클릭.

1. 삭제 :
    1) 삭제 확인창 "삭제하시겠습니까?" 확인 버튼 클릭
    2) post table 에 delete_whether (BOOLEAN)을 1(true)로 변경한다.
    3) 삭제 알림창 "삭제되었습니다."
    4) 검색페이지로 이동 (delete_whether 가 1인 것은 검색페이지에 제공 하지 않는다.)

#### 3. 페이징

1. 필요한 정보 : 1) 한 페이지 목록 개수(15개) , 2) 페이지 개수(10개), 3) 현재 페이지 번호 4) 검색 항목, 검색 값<br>
   발전사항 : 1) 1~10에 '맨 뒤로' 2) 중간에 '맨 앞으로', '맨 뒤로' 3) 마지막 '맨 앞으로' 버튼 추가하기
2. 어떻게 구현할 지? :
   어떤 것을 사용할 지? html, jsp, js 중 jsp 선택. jsp 안의 jsp. search.jsp 안에 list component, page component.
    1) controller -> service -> dao -> service -> controller -> view 순서로 진행.
    2) client -> server 요청 정보 : 현재 페이지, 검색 항목, 검색 내용 / 응답 정보 : 전체 목록 개수, 게시물 리스트 객체 <br>
    3) 코드 <br>

생각할 부분 :

- 등록 후에 상세페이지로 이동한다면, 등록한 게시물을 어떻게 찾을지. (일단, 가장 최신에 등록한 post_id를 가져왔음. 여러 사람이 사용한다면 최신 등록물이 자신이 등록한
  것이 아닐 수 있음.)

해결할 부분 :

- Dao를 service로 분리하기.
- localhost:8080 -> index.html
- 경로:
    1) JSP 에서 js, css 정적파일 "Absolute paths not recommended in JSPs"
    2) Controller(PostController)에서 리다이렉트 29, 47 line "redirect:/detail/ "
- 등록 유효성 다시 생각하기.    

