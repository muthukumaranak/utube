package com.app.youtubeclone.service.impl;

import com.app.youtubeclone.entity.MediaComment;
import com.app.youtubeclone.entity.MediaFile;
import com.app.youtubeclone.entity.Users;
import com.app.youtubeclone.repository.MediaCommentRepo;
import com.app.youtubeclone.repository.MediaFileRepo;
import com.app.youtubeclone.repository.UsersRepo;
import com.app.youtubeclone.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaFileRepo mediaFileRepo;

    @Autowired
    private MediaCommentRepo mediaCommentRepo;

    @Autowired
    UsersRepo usersRepo;


    @Override
    public Page<MediaFile> findPage(int pageNo, int pageSize, String sortField, String sortDirection,String keyword,String tag) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        if (keyword != null) {
            return mediaFileRepo.search(keyword, pageable);
        }
        if(tag != null){
            return mediaFileRepo.filterByTag(tag,pageable);
        }
        return this.mediaFileRepo.findAll(pageable);
    }

    @Override
    public String createComment(String id, String comment) {
        try{
            System.err.println("test point 1");
            MediaFile mediaFile = new MediaFile();
            mediaFile.setId(Integer.parseInt(id));
            System.err.println("test point 1");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Users users = usersRepo.findByEmail(auth.getName());

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String created_at = f.format(new Date());
            MediaComment mediaComment = new MediaComment(users.getName(), comment, mediaFile, created_at);
            mediaCommentRepo.save(mediaComment);
            return "redirect:/";
        }catch (Exception e){
            System.out.println(e);
            return "redirect:/";
        }
    }



    @Override
    public String like(String id) {
        try{
            int total_likes = mediaFileRepo.findLikes(Integer.parseInt(id));
            mediaFileRepo.updateLikes(total_likes+1,Integer.parseInt(id));
            return "redirect:/";
        }catch (Exception e){
            System.out.println(e);
            return "redirect:/";
        }
    }

    @Override
    public String dislike(String id) {
        try{
            int total_likes = mediaFileRepo.findDisLikes(Integer.parseInt(id));
            mediaFileRepo.updateDisLikes(total_likes+1,Integer.parseInt(id));
            return "redirect:/";
        }catch (Exception e){
            System.out.println(e);
            return "redirect:/";
        }
    }

    @Override
    public String views(int id) {
        try{
            int total_views = mediaFileRepo.findViews(id);
            System.err.println(total_views);
            mediaFileRepo.updateViews(total_views+1,id);
            System.err.println("views increased");
            return "View Increased";
        }catch (Exception e){
            System.out.println(e);
            return "Not added";
        }
    }

    @Override
    public void deletevideo(int id) {
        mediaFileRepo.deleteById(id);
    }

}
