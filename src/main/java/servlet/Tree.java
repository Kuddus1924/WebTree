package servlet;

import reader.JsonConvert;
import reader.NodeReader;
import reader.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/tree"})
public class Tree extends HttpServlet {
    private Reader reader;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        String nodeId = request.getParameter("id");
        String category = request.getParameter("action");
        PrintWriter out = response.getWriter();
        try {
            if (category != null)
            {
                if (category.equals("move_category")) {
                    reader.remove(nodeId, request.getParameter("new_parent"));
                }
                else if(category.equals("rename_category")) {
                    reader.rename(nodeId, request.getParameter("text"));
                }
                else if(category.equals("delete_category")){
                    reader.delete(nodeId);
                }
                else if(category.equals("create_category")){
                reader.add(request.getParameter("position"),request.getParameter("text"));
                }
            }
            else if (nodeId.equals("root")) {
                out.println(JsonConvert.toJSON_String(reader.getRoot()));
            }
            else
                out.println(JsonConvert.toJSON_String(reader.getById(nodeId)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            out.close();
        }
    }
    @Override
    public void init() throws ServletException {
        super.init();
        reader = new NodeReader();
    }
}
