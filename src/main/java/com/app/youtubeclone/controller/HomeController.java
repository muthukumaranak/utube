package com.app.youtubeclone.controller;

import com.app.youtubeclone.entity.*;
import com.app.youtubeclone.repository.ChannelRepo;
import com.app.youtubeclone.repository.LibraryRepo;
import com.app.youtubeclone.repository.MediaFileRepo;
import com.app.youtubeclone.repository.UsersRepo;
import com.app.youtubeclone.service.ChannelService;
import com.app.youtubeclone.service.CommentService;
import com.app.youtubeclone.service.MediaService;
import com.app.youtubeclone.service.UserService;
import com.app.youtubeclone.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private MediaFileRepo mediaFileRepo;

    @Autowired
    private LibraryRepo libraryRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ChannelRepo channelRepo;


    @Autowired
    UserService userService;

    @Autowired
    ChannelService channelService;

    public String sessionUser;
    public String sessionEmail;
    public int sessionId;


    @GetMapping("/")
    public String viewHomePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByEmail(auth.getName());
        System.err.println(auth.getName());
        return findPage(1, "title",
                "asc", "", "", model);
    }

    @GetMapping("/home")
    public String HomePage(Model model, String sessionEmail, String sessionUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByEmail(auth.getName());
        System.err.println(auth.getName());
        return findPage(1, "title",
                "asc", "", "", model);
    }

    @GetMapping("/postvideo")
    public String postvideo(){
        return "create";
    }

    @GetMapping("/create")
    public String create() {
        return "channelRegistration";
    }

    @GetMapping("/channel")
    public String channel() {
        return "channelRegistration";
    }

    @GetMapping("/loginpage2")
    public String login(Model model){
       // model.addAttribute("name",sessionName);
        return "redirect:/";
    }


    @GetMapping("/loginpage")
    public String login2(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/";
    }

    @PostMapping("/user")
    public String userRegister(@RequestParam String name, @RequestParam String email,
                               @RequestParam String password){
        return userService.register(name,email,password);
    }

    @GetMapping("/mylibrary")
    public String mylibrary(Model model){
        List<MediaFile> mediaFiles = mediaFileRepo.findTop2ByOrderByViewsDesc();
        List<MediaFile> list = new LinkedList<>();
        mediaFiles.forEach(video -> {
            MediaFile mediaFileDTO = new MediaFile();
            mediaFileDTO.setTitle(video.getTitle());
            mediaFileDTO.setDescription(video.getDescription());
            mediaFileDTO.setId(video.getId());
            mediaFileDTO.setOwner(video.getOwner());
            mediaFileDTO.setThumbnailUrl(video.getThumbnailUrl());
            mediaFileDTO.setVideoUrl(video.getVideoUrl());
            mediaFileDTO.setTag(video.getTag());
            mediaFileDTO.setRestriction(video.getRestriction());
            mediaFileDTO.setCreatedAt(video.getCreatedAt());
            mediaFileDTO.setVisibility(video.getVisibility());
            mediaFileDTO.setLikes(video.getLikes());
            mediaFileDTO.setDislikes(video.getDislikes());
            mediaFileDTO.setViews(video.getViews());
            mediaFileDTO.setDuration(video.getDuration());
            List<MediaComment> commentDTOS = new LinkedList();
            video.getMediaComment().forEach(comment -> {
                MediaComment commentDTO = new MediaComment();
                commentDTO.setCommentby(comment.getCommentby());
                commentDTO.setComment(comment.getComment());
                commentDTO.setId(comment.getId());
                commentDTO.setMediaFile(comment.getMediaFile());
                commentDTO.setCreated_at(comment.getCreated_at());
                commentDTOS.add(commentDTO);
            });
            mediaFileDTO.setMediaComment(commentDTOS);
            list.add(mediaFileDTO);
        });
        model.addAttribute("list", list);
        return "mylibrary";
    }

    @GetMapping("/mychannel")
    public String mychannel(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByEmail(auth.getName());
        List<MediaFile> mediaFiles = mediaFileRepo.myvideos(users.getEmail());
        List<MediaFile> list = new LinkedList<>();
        mediaFiles.forEach(video -> {
            MediaFile mediaFileDTO = new MediaFile();
            mediaFileDTO.setTitle(video.getTitle());
            mediaFileDTO.setDescription(video.getDescription());
            mediaFileDTO.setId(video.getId());
            mediaFileDTO.setOwner(video.getOwner());
            mediaFileDTO.setThumbnailUrl(video.getThumbnailUrl());
            mediaFileDTO.setVideoUrl(video.getVideoUrl());
            mediaFileDTO.setTag(video.getTag());
            mediaFileDTO.setRestriction(video.getRestriction());
            mediaFileDTO.setCreatedAt(video.getCreatedAt());
            mediaFileDTO.setVisibility(video.getVisibility());
            mediaFileDTO.setLikes(video.getLikes());
            mediaFileDTO.setDislikes(video.getDislikes());
            mediaFileDTO.setViews(video.getViews());
            mediaFileDTO.setDuration(video.getDuration());
            List<MediaComment> commentDTOS = new LinkedList();
            video.getMediaComment().forEach(comment -> {
                MediaComment commentDTO = new MediaComment();
                commentDTO.setCommentby(comment.getCommentby());
                commentDTO.setComment(comment.getComment());
                commentDTO.setId(comment.getId());
                commentDTO.setMediaFile(comment.getMediaFile());
                commentDTO.setCreated_at(comment.getCreated_at());
                commentDTOS.add(commentDTO);
            });
            mediaFileDTO.setMediaComment(commentDTOS);
            list.add(mediaFileDTO);
        });
        model.addAttribute("list", list);
        List<Channel> channelList = channelRepo.findByOwner(users.getEmail());
        List<Channel> channels = new LinkedList<>();

        channelList.forEach(channelcontent -> {
                    Channel channel = new Channel();
                    channel.setChannel(channelcontent.getChannel());
                    channel.setCoverUrl(channelcontent.getCoverUrl());
                    channel.setDescription(channelcontent.getDescription());
                    channel.setSubscribers(channelcontent.getSubscribers());
                    channel.setCreatedAt(channelcontent.getCreatedAt());
                    channels.add(channel);
                });
        model.addAttribute("channel",channels);
        return "mychannel";
    }

    @GetMapping("/page/{pageNo}")
    public String findPage(@PathVariable(value = "pageNo") int pageNo,
                           @RequestParam("sortField") String sortField,
                           @RequestParam("sortDirection") String sortDirection,
                           @RequestParam(value = "keyword", required = false) String keyword,
                           @RequestParam(value = "tag", required = false) String tag,
                           Model model) {
        final int PAGE_SIZE = 2;
        Page<MediaFile> page = mediaService.findPage(pageNo, PAGE_SIZE, sortField, sortDirection, keyword, tag);
        List<MediaFile> mediaFiles = page.getContent();
        List<MediaFile> listDTOS = new LinkedList<>();
        mediaFiles.forEach(video -> {
            MediaFile mediaFileDTO = new MediaFile();
            mediaFileDTO.setTitle(video.getTitle());
            mediaFileDTO.setDescription(video.getDescription());
            mediaFileDTO.setId(video.getId());
            mediaFileDTO.setOwner(video.getOwner());
            mediaFileDTO.setThumbnailUrl(video.getThumbnailUrl());
            mediaFileDTO.setVideoUrl(video.getVideoUrl());
            mediaFileDTO.setTag(video.getTag());
            mediaFileDTO.setRestriction(video.getRestriction());
            mediaFileDTO.setCreatedAt(video.getCreatedAt());
            mediaFileDTO.setVisibility(video.getVisibility());
            mediaFileDTO.setLikes(video.getLikes());
            mediaFileDTO.setDislikes(video.getDislikes());
            mediaFileDTO.setViews(video.getViews());
            mediaFileDTO.setDuration(video.getDuration());
            List<MediaComment> commentDTOS = new LinkedList();
            video.getMediaComment().forEach(comment -> {
                MediaComment commentDTO = new MediaComment();
                commentDTO.setCommentby(comment.getCommentby());
                commentDTO.setComment(comment.getComment());
                commentDTO.setId(comment.getId());
                commentDTO.setMediaFile(comment.getMediaFile());
                commentDTO.setCreated_at(comment.getCreated_at());
                commentDTOS.add(commentDTO);
            });
            mediaFileDTO.setMediaComment(commentDTOS);
            listDTOS.add(mediaFileDTO);
        });
        model.addAttribute("list", listDTOS);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("keyword", keyword);
        model.addAttribute("tag", tag);
        model.addAttribute("mediaFiles", mediaFiles);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByEmail(auth.getName());
        if(users != null){
            model.addAttribute("sessionUser",users.getName());
            model.addAttribute("sessionId",users.getId());
            model.addAttribute("hasChannel",users.getStatus());
        }

        return "home";
    }

    @GetMapping("/trending")
    public String trending(Model model) {
        List<MediaFile> mediaFiles = mediaFileRepo.findTop2ByOrderByViewsDesc();
        List<MediaFile> list = new LinkedList<>();
        mediaFiles.forEach(video -> {
            MediaFile mediaFileDTO = new MediaFile();
            mediaFileDTO.setTitle(video.getTitle());
            mediaFileDTO.setDescription(video.getDescription());
            mediaFileDTO.setId(video.getId());
            mediaFileDTO.setOwner(video.getOwner());
            mediaFileDTO.setThumbnailUrl(video.getThumbnailUrl());
            mediaFileDTO.setVideoUrl(video.getVideoUrl());
            mediaFileDTO.setTag(video.getTag());
            mediaFileDTO.setRestriction(video.getRestriction());
            mediaFileDTO.setCreatedAt(video.getCreatedAt());
            mediaFileDTO.setVisibility(video.getVisibility());
            mediaFileDTO.setLikes(video.getLikes());
            mediaFileDTO.setDislikes(video.getDislikes());
            mediaFileDTO.setViews(video.getViews());
            mediaFileDTO.setDuration(video.getDuration());
            List<MediaComment> commentDTOS = new LinkedList();
            video.getMediaComment().forEach(comment -> {
                MediaComment commentDTO = new MediaComment();
                commentDTO.setCommentby(comment.getCommentby());
                commentDTO.setComment(comment.getComment());
                commentDTO.setId(comment.getId());
                commentDTO.setMediaFile(comment.getMediaFile());
                commentDTO.setCreated_at(comment.getCreated_at());
                commentDTOS.add(commentDTO);
            });
            mediaFileDTO.setMediaComment(commentDTOS);
            list.add(mediaFileDTO);
        });
        model.addAttribute("list", list);
        return "home";
    }

    @GetMapping("/watchLater")
    public String watchLater(Model model) {
        // List<MediaFile> mediaFiles = mediaFileRepo.findAllWatchLater();
        // model.addAttribute("mediaFiles",mediaFiles);
        MediaComment mediaComment = new MediaComment();
        model.addAttribute("mediaComment", mediaComment);
        return "home";
    }

    @GetMapping("/setWatchLater/{id}")
    public String setWatchLater(@ModelAttribute("mediaComment") MediaComment comment, Model model, @PathVariable("id")
            int mediaId, @ModelAttribute("mediaFiles") MediaFile mediaFiles) {
        MediaFile mediaFile = mediaFileRepo.findById(mediaId).get();
        model.addAttribute("mediaComment", comment);
        model.addAttribute("mediaFiles", mediaFiles);
        return "redirect:/";

    }

    @GetMapping("/watchLater/{userId}/{mediaId}")
    public String watchLater(@RequestParam("userId") int userId, @RequestParam("mediaId") int mediaId, Model model) {
        Library library = new Library();
        library.setUserId(userId);
        library.setVideoId(mediaId);
        libraryRepo.save(library);
        return "redirect:/";
    }

    @GetMapping("/savedVideo")
    public String savedVideo(@RequestParam("userId") int userId, Model model) {
        List<MediaFile> mediaFiles = libraryRepo.findAllByUserId(userId);
        System.out.println("files : " + libraryRepo.findAllByUserId(userId));
        model.addAttribute("mediaFiles", mediaFiles);
        MediaComment mediaComment = new MediaComment();
        model.addAttribute("mediaComment", mediaComment);
        return "home";
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "userRegistration";
    }

    @PostMapping("/userRegistration")
    public String userRegistration(@RequestParam("name") String name, @RequestParam("email") String email,
                                   @RequestParam("password") String password){
        return userService.register(name, email, password);
    }

    @PostMapping("/channel")
    public String channelRegistration(@RequestParam String name, @RequestParam String description,
                                      @RequestParam("thumbnail") MultipartFile thumbnail){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByEmail(auth.getName());
        return channelService.register(name, description, thumbnail, users.getEmail());
    }

    @PostMapping("/deletecomment")
    public String deleteComment(@RequestParam int id){
        System.err.println(id);
        commentService.deleteCommentById(id);
        return "redirect:/mychannel";
    }

    @PostMapping("/deletemycomment")
    public String deletemyComment(@RequestParam int id){
        System.err.println(id);
        commentService.deleteCommentById(id);
        return "redirect:/";
    }



    @PostMapping("/deletevideo")
    public String deletevideo(@RequestParam int id){
        System.err.println(id);
        mediaService.deletevideo(id);
        return "redirect:/mychannel";
    }

}

