package dev.spring.demo.Data;

import java.util.ArrayList;
import java.util.List;

import dev.spring.demo.Controller.BlogController;

public class BlogMockedData {

    private List<Blog> blogs;

    /**Since we want our Data Class to be Singleton to mock the behaviour of a DB (DBs are Singleton
    * in nature). We make a variable instance and initialise it to null. Then using
    * getInstance function we check if its initialised earlier if yes we return the old instance or
    * we create a new instance using the new key word.
    * Could have use the Singleton Annotation directly as well? No Looks like Singleton annotation
    * can only be used by JavaBeans and not with class.
    * */
    private static BlogMockedData instance = null;
    public static BlogMockedData getInstance(){
        if (instance == null){
            instance = new BlogMockedData();
        }
        return instance;
    }

    public BlogMockedData(){
        blogs = new ArrayList<Blog>();

        blogs.add(new Blog(1,"Go up, up and away with your Google Assistant",
                "With holiday travel coming up, and 2018 just around the corner, " +
                        "you may be already thinking about getaways for next year. Consider " +
                        "the Google Assistant your new travel buddy, ready at the drop of a hat—or a passport"));

        blogs.add(new Blog(2,"Get local help with your Google Assistant",
                "No matter what questions you’re asking—whether about local traffic or " +
                        "a local business—your Google Assistant should be able to help. And starting " +
                        "today, it’s getting better at helping you, if you’re looking for nearby services " +
                        "like an electrician, plumber, house cleaner and more"));

        blogs.add(new Blog(3,"The new maker toolkit: IoT, AI and Google Cloud Platform",
                "Voice interaction is everywhere these days—via phones, TVs, laptops and smart home devices " +
                        "that use technology like the Google Assistant. And with the availability of maker-friendly " +
                        "offerings like Google AIY’s Voice Kit, the maker community has been getting in on the action " +
                        "and adding voice to their Internet of Things (IoT) projects."));

        blogs.add(new Blog(4, "Learn more about the world around you with Google Lens and the Assistant",
                "Looking at a landmark and not sure what it is? Interested in learning more about a movie as " +
                        "you stroll by the poster? With Google Lens and your Google Assistant, you now have a helpful " +
                        "sidekick to tell you more about what’s around you, right on your Pixel."));

        blogs.add(new Blog(5, "7 ways the Assistant can help you get ready for Turkey Day",
                "Thanksgiving is just a few days away and, as always, your Google Assistant is ready to help. " +
                        "So while the turkey cooks and the family gathers, here are some questions to ask your Assistant."));
    }

    // This function will return the entire blog collection.
    public List<Blog> fetchBlog(){
        return blogs;
    }

    // This function will return the requested blog whose Id was passed in parameter.
    public Blog getBlogById(int id){
        for(Blog b: blogs){
            if(b.getId() == id){
                return b;
            }
        }
        return null;
    }

    // This function will return blog based on Search.
    public List<Blog> searchBlog(String searchItem){
        List<Blog> searchedBlog = new ArrayList<Blog>();
        for(Blog b: blogs){
            if (b.getTitle().toLowerCase().contains(searchItem.toLowerCase()) ||
                    b.getContent().toLowerCase().contains(searchItem.toLowerCase())) {
                searchedBlog.add(b);
            }
        }
        return searchedBlog;
    }

    // This function will create a new Blog/ Add it to the List.
    public Blog createBlog(int id, String title, String content){
        Blog newBlog = new Blog(id,title,content);
        blogs.add(newBlog);
        return newBlog;
    }

    //This function will update the content of Blog
    public Blog updateBlog(int id, String title, String content){
        for(Blog b: blogs){
            if(b.getId() == id){
                int blogIndex = blogs.indexOf(b);
                b.setTitle(title);
                b.setContent(content);
                blogs.set(blogIndex,b);
                return b;
            }
        }
        return null;
    }

    // This function will handle Delete operations.
    public boolean deleteBlogById(int id){
        int blogIndex = -1;
        for(Blog b: blogs){
            if(b.getId() == id){
                blogIndex = blogs.indexOf(b);
                continue;
            }
        }

        if(blogIndex>-1){
            blogs.remove(blogIndex);
        }
        return true;
    }

}
