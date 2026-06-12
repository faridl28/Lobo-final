package com.example.blog.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.http.*;
import java.net.URI;

@WebServlet(name="PostListServlet", urlPatterns={"/posts"})
public class PostListServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8080/api/posts"))
        .GET()
        .build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      req.setAttribute("postsJson", response.body());
    } catch (Exception e) {
      req.setAttribute("postsJson", "[]");
    }
    req.getRequestDispatcher("/WEB-INF/views/posts.jsp").forward(req, resp);
  }
}
