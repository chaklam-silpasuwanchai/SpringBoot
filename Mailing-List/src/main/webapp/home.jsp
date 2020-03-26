<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mailing List App</title>
</head>

	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<script>
            $.getJSON('/findUnsubscribed', function (data) {
         	
                $("ul#unsub > li").remove();
               
                $.each(data, function (key, value) {
                    $("#unsub").append('<li>' + value['title'] +  
                    		' | ' +  '<a href="subscribe/'+ value['id']+'">Subscribe</a>' +  '</li>');
                });
            });

            $.getJSON('/findSubscribed', function (data) {
            	
                $("ul#sub > li").remove();

                $.each(data, function (key, value) {
                    $("#sub").append('<li>' + value['title'] +  
                    		' | ' +  '<a href="unsubscribe/'+ value['id']+'">Unsubscribe</a>' +  '</li>');
                });
            });
       
    </script>
        
      </head>
      <body>
      <h1>User Dashboard</h1>
      
      <h3>Available mailing list</h3>
      	<a href="/logout">Logout</a>
        <div>
	        <ul id="unsub">
	
	        </ul>
    	</div>
    	<Br>
    <h3>You are currently subscribed to these following mailing lists</h3>
    	<div>
	        <ul id="sub">
	
	        </ul>
    	</div>
    	
    
    
      </body>
</html>