<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Listado de Posts</title>
</head>
<body>
  <h1>Posts</h1>
  <div id="posts"></div>
  <script>
    const postsJson = '<c:out value="${postsJson}" />';
    const posts = JSON.parse(postsJson);
    const container = document.getElementById('posts');
    if(posts.length===0){ container.innerHTML = '<p>No hay posts</p>'; }
    else {
      posts.forEach(p=>{
        const el = document.createElement('div');
        el.innerHTML = `<h2>${p.titulo}</h2><p>${p.contenido}</p><small>Autor: ${p.autorUsername}</small>`;
        container.appendChild(el);
      });
    }
  </script>
</body>
</html>
