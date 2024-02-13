const { func } = require("assert-plus");

function likeButton(id) {
	const ele = document.getElementById(id);
	ele.setAttribute("src", ".images/liked.png");
	console.log("Like button", ele, id);
}
function navigateBack() {
	document.querySelector(".navigation-section2").style.display = "none";
	document.querySelector(".tweet-box2").style.display = "block";
}
function floatNavigation() {
	document.querySelector(".navigation-section2").style.display = "block";
	document.querySelector(".tweet-box2").style.display = "none";
}
function postSection() {
	const textData = document.querySelectorAll(".post-input")[1].value;
	const html = `
    <div class="post-header">
        <div class="profile-icon">
            <img src="images/profile pic.png" alt="profile icon" />
        </div>
        <div class="user">Nitesh Gupta</div>
        <div class="username">@nit_hck</div>
        <div class="dot">.</div>
        <div class="time">34s</div>
        <div>
            <img id="more-icon" src="images/more.png" alt="more" />
        </div>
    </div>
    <div class="post-tweet">
        
    ${textData}
        
    </div>
    
    <div class="post-images">
        <img id="comment-icon" src="images/comment.png" alt="comment" />
        <img id="retweet-icon" src="images/retweet.png" alt="retweet" />
        <img id="like-icon" src="images/like.png" alt="like" />
        <img id="poll-icon" src="images/poll.png" alt="poll" />
        <img id="bookmark-icon" src="images/bookmark.png" alt="bookmark" />
        <img id="upload-icon" src="images/upload.png" alt="upload" />
    </div>`;
	//console.log(html);
	const newDiv = document.createElement("div");
	newDiv.innerHTML = html;
	newDiv.classList.add("post-text");
	document.querySelectorAll(".posts")[1].appendChild(newDiv);
}
