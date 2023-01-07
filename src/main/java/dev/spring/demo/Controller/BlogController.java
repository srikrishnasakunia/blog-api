package dev.spring.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import dev.spring.demo.Data.Blog;
import dev.spring.demo.Data.BlogRepository;

@RestController
public class BlogController {

    //BlogMockedData blogMockedData = BlogMockedData.getInstance();

    @Autowired
    BlogRepository blogRepository;

   /* @RequestMapping("/")
    public String index(){
        return "Congratulations from Blog Controller";
    }*/

    @GetMapping("/blog")
    public List<Blog> index(){
        //return blogMockedData.fetchBlog();
        return blogRepository.findAll();
    }


    /** Since there is chance that we might not get the element during search, we implemented
     an else condition that returns us with Null to handle the Null condition. */
    @GetMapping("/blog/{id}")
    public Blog show(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        return blogRepository.findById(blogId).orElse(null);
    }

    @PostMapping(value = "/blog/search", consumes = {"application/json"})
    public List<Blog> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return blogRepository.findByTitleOrContent(searchTerm,searchTerm);
        //return blogMockedData.searchBlog(searchTerm);
    }

    /**
     * If we are accepting something in body we need to add a new parameter that is consumes
     * to specify what kind of data are we accepting, i.e., json, xml, text.*/
    @PostMapping(value = "/blog", consumes = {"application/json"})
    public Blog create(@RequestBody Map<String,String> body){
        int id = Integer.parseInt(body.get("id"));
        String title = body.get("title");
        String content = body.get("content");
        return blogRepository.save(new Blog(title,content));
        // return blogMockedData.createBlog(id,title,content);
    }

    @PutMapping(value = "/blog/{id}",consumes = {"application/json"})
    public Blog update(@PathVariable String id, @RequestBody Map<String,String> body){
        int blogId = Integer.parseInt(id);
        Blog blog = blogRepository.findById(blogId).orElse(null);
        blog.setContent(body.get("content"));
        blog.setTitle(body.get("title"));
        return blogRepository.save(blog);
        /* String title = body.get("title");
        String content = body.get("content");
        return blogMockedData.updateBlog(blogId,title,content);*/
    }

    @DeleteMapping("/blog/{id}")
    public boolean delete(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        //return blogMockedData.deleteBlogById(blogId);
        blogRepository.delete(blogRepository.getReferenceById(blogId));
        return true;
    }
}
