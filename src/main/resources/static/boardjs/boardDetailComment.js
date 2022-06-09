// 게시판 상세보기 댓글 작성
$(document).ready(function(){
 $("#comment-btn").click(function(){
        var comment = $("#comments").val();
        var board_Code =  $('input[name=board_Code]').val();

        $.ajax({
            type : "POST",
            headers : { "Content-type" : "application/json", "X-HTTP-Method-Override" : "POST" },
            url : "/boardComment.do",
            data : JSON.stringify({
                comment:comment,
                board_Code:board_Code,
                writer:"관리자"
                }),
            dataType:"text",
            success : function (data) {
                console.log("result : " + data);
            }
        });
    });
});