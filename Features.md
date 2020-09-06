## Technology Stack

    1. Spring boot web
    2. Thymeleaf
    3. Spring security + JWT
    4. PostgreSQL
    5. Spring Jpa + Hibernate 
    6. HTML, CSS and JS


## Use Cases:

### Non-logged in User:

1. Non-logged and Logged in user should be able to see list of videos thumbnail that has videos, title, creator, views, upload date.

2. Non-logged and Logged in user should be able to see full videos that has videos, title, views, upload date, likes, dislike, share, save, creator and its subscribes, videos description, subscribe button.

3. Non-logged and Logged in user should be able to see all comments and number of comments.

4. Non-logged and Logged in user should be able to see comment with username and date created at, like/or/dislike , and all replies.

5. Non-logged and Logged in user should be able to navigate to up next videos.

6. Non-logged and Logged in user should be able to search videos by keywords.

7. Non-logged and Logged in user should be able to filter videos by Music, Sports, Gaming, Movies, News, Live, Fashion & Beauty, Learning.

8. Non-logged and Logged in user should be able to see trending videos[50] list based on number views.

9. Non-logged and Logged in user should be able to sort comments by newest first[based on time created].

10. Non-logged and Logged in user should be able to sort comments by top comments [ based on number of replies and likes ].

11. Non-logged and Logged in user should be able to sign in and register.

12. Non-logged and logged in user should be able to share videos.

### Logged in User:

1. Logged in user should be able to comment and reply on videos.

2. Logged in user should be able to like/or/dislike videos.

3. Logged in user should be able to share, save and subscribe videos.

4. Logged in user should be able to filter by tags recommended.

5. Logged in user should be able to see Library that has history, watch later, liked videos and my videos by that user.

6. Logged in user should be able to create videos with :

    ### Details:

        1. Title
        2. Description
        3. Thumbnail
        4. Playlist
        5. Audience
        6. Paid Promotions
        7. Tags
        8. Age restrictions 
        9. Language, subtitle, closed captions
        10. Category [ Films and Animation, Music, People and Blogs, Auto ans vehicles, Education, Comedy etc]
        11. Add an end screen
        12. Visibility [public, private ]

7. Logged in user should be able to create draft.

8. Logged in user should be able to see thier uploads with visibility, restrictions, date, views, comments, like/dislikes and it one page can have 30 rows of information.

9. Logged in user should be able to edit draft.

## Details after uploading: 

10. Logged in user should be able to edit thier videos with :

    ### Details:

        1. Description and title.
11. Logged in user should be able to download, delete their videos.   


### Target 

1. Technology stack.
2. User stories.
3. File upload, download, videos streaming in Spring boot.
4. db schema

## Database Schema

#### User
1. ID
2. User Name
3. Email
4. Password

#### Subscriptions
1. ID
2. User ID
3. List of Channel ID's

#### Channel
1. ID
2. Channel Name
3. Title
4. Creator ID
5. Description
6. Subscribers

#### Media
1. ID
2. Blob ID
3. Uploaded By
4. Uploaded At
5. Thumbnail ID
6. Comments
7. Likes 
8. Dislikes
9. Views

#### Media Details
1. ID
2. Video Title
3. Thumbnail image
4. Video File (BLOB)
5. Description

#### Comments
1. ID
2. Comment
3. Commented By
4. Media ID
5. Created at

#### Likes and Dislikes
1. ID
2. Media ID
3. Likes
4. Dislikes

#### Tags
1. ID
2. Tag
3. Media ID

#### Play List
1. ID
2. Owner
3. Title
4. Tag
5. Media ID
6. Files to be included

