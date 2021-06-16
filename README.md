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
Maven Repository에서 Spring Web MVC에 필요한 라이브러리 Jar파일 다운.
spring-mvc-board/lib에 Jar파일 저장.</br>
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
    -  ```<servlet>```선언 : 
    -  ```<servlet-name>``` : DispacherServlet이 로드되면 서블릿 이름을 기반으로 명명된 XML 파일에서 스프링 애플리케이션 컨텍스트를 로드한다.
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

####1-6 JDBC Template
NamedParameterJdbcTemplate class 사용.
```
Spring JDBC » 5.3.7
pring Transaction » 5.3.7
```



