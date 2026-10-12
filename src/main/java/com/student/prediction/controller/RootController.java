package com.student.prediction.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String rootPage() {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta http-equiv="refresh" content="2;url=http://localhost:5173">
                <title>EduPredict AI - Backend API</title>
                <style>
                    body {
                        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
                        background: #f0f7ff;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        height: 100vh;
                        margin: 0;
                    }
                    .card {
                        background: #ffffff;
                        padding: 40px;
                        border-radius: 20px;
                        box-shadow: 0 10px 30px rgba(37, 99, 235, 0.15);
                        text-align: center;
                        max-width: 520px;
                        border: 1px solid #bfdbfe;
                    }
                    h1 { color: #1e3a8a; font-size: 1.8rem; margin-bottom: 8px; }
                    p { color: #475569; font-size: 1rem; line-height: 1.5; }
                    .status {
                        display: inline-block;
                        background: #ecfdf5;
                        color: #047857;
                        padding: 6px 16px;
                        border-radius: 9999px;
                        font-weight: 600;
                        font-size: 0.85rem;
                        margin-bottom: 20px;
                        border: 1px solid #a7f3d0;
                    }
                    .btn {
                        display: inline-block;
                        margin-top: 20px;
                        background: linear-gradient(135deg, #2563eb, #1d4ed8);
                        color: #ffffff;
                        padding: 12px 28px;
                        border-radius: 10px;
                        text-decoration: none;
                        font-weight: 600;
                        box-shadow: 0 4px 12px rgba(37, 99, 235, 0.3);
                        transition: transform 0.2s;
                    }
                    .btn:hover { transform: translateY(-2px); }
                </style>
            </head>
            <body>
                <div class="card">
                    <span class="status">&#10004; Backend API is Running on Port 8080</span>
                    <h1>EduPredict AI Backend</h1>
                    <p>The Spring Boot REST API is online and healthy. The interactive web application user interface is hosted on the React frontend at <strong>Port 5173</strong>.</p>
                    <p>Redirecting you to the frontend in 2 seconds...</p>
                    <a href="http://localhost:5173" class="btn">Open Frontend Dashboard &rarr;</a>
                </div>
            </body>
            </html>
            """;
    }
}
