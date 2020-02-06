<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label for="title">タスク内容</label><br/>
<textarea name="content" cols="50" rows="5"></textarea>
<br/><br/>

<input type="hidden" name="_token" value="${_token}"/>
<button type="submit">登録</button>