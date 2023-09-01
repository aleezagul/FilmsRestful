function getRequestObject()
{
	if(window.XMLHttpRequest){
		return (new XMLHttpRequest());
		} else if(window.ActiveXObject){
			return(new ActivexoBJECT("Microsoft.XMLHTTP"));
			
		}else{
			return(null);
		}
	}
	        
function films(address, resultRegion){
	var address="Restful?Accept=" + address;
	ajaxResult(address,resultRegion,function(request){restfulFilms(request,resultRegion);});
	}
	
	function restfulFilms(request,resultRegion){
		if((request.status==200)&&(request.readyState==4)){
			var data=request.responseText.toString();
			var splitter = data.split(/[\n\r]+/);
			var arraylist = new Array(data.length -1);
			
			var button=document.createElement('button');
						var remove=document.createElement('button');
				button.innerHTML= 'update';
				remove.innerHTML='delete';
			for ( var i=1;i<data.length; i++){
				arraylist[i-1]=data[i].split("|");
				document.body.appendChild(remove);
						document.body.appendChild(button);
						var button=document.createElement('button');
						var remove=document.createElement('button');

				}
				
			var stringify = arraylist.toString();
				var replace = stringify.replace(/,/g , "");
				
						var result="";
					splitter.forEach(splittertext);
					function splittertext(i,splitter){
						var films =i.split("\\|");
						result= splitter.id;
						
						console.log("1");
						
				button.innerHTML= 'update';
				remove.innerHTML='delete';
				function text(filmid){
					
					button.addEventListener("click",function(){
						filmsPut(filmid,"application/text");
					});
					}
					function deletetext(filmid){
					
					remove.addEventListener("click",function(){
						filmsDelete(filmid,"application/text");
					});
					}
					console.log(result);
					console.log(splitter.id);
						deletetext(films[0]);
						text(films[0]);

					
					console.log(result);
					console.log(splitter.id);
						

					}
							
			console.log(arraylist);
			console.log(splitter);
			
			htmlInsert(resultRegion,splitter);
		}
		
	}
	                	        
function ajaxResult(address,resultRegion,responseHandler){
	var request = getRequestObject();
	console.log("01");
	request.onreadystatechange=
	
	function(){
		responseHandler(request,resultRegion)
		}
		request.open("GET", address, resultRegion);
		request.send(null);
	}
	
function showResponseText(request, resultRegion){
		if((request.readyState == 4) && 
		(request.status == 200)){
			htmlInsert(resultRegion, request.responseText);
			
			}
			}
			
function htmlInsert(id,htmlData){
				document.getElementById(id).innerHTML = htmlData;
			
}
function getValue(id){
	return(escape(document.getElementById(id).value));
	
}


function ajaxPost(address,data,responseHandler,format){
	var request= getRequestObject(); 
	console.log("01");
	request.onreadystatechange=
	
	function(){
		responseHandler(request);
		
		};
		console.log(data);
		console.log(format);
		request.open("POST", address, true);
		request.setRequestHeader("Content-Type",format); //setting content-type to the format selected(application/text)
		request.send(data);
	}
	
	function postFilms(request){
		if((request.status==200)&&(request.readyState==4)){
			var data=request.responseText.toString();
			var splitter = data.split(/[\n\r]+/);
			var arraylist = new Array(data.length -1);
			
			
			for ( var i=1;i<data.length; i++){
				arraylist[i-1]=data[i].split("|");
			}
			var stringify = arraylist.toString();
				var replace = stringify.replace(/,/g , "");
			
			
			console.log(arraylist);
			console.log(splitter);
			
		}
		}
		function filmsPost(format){
			var fm=format;
	var address="Restful";
	var title= document.getElementById("title").value; //gets data
	var year= document.getElementById("year").value; 
	var director= document.getElementById("director").value; 
	var stars= document.getElementById("stars").value; 
	var review= document.getElementById("review").value; //gets data
	var text=title+"|"+year+"|"+director+"|"+stars+"|"+review;	
	
	
	console.log(text);
	ajaxPost(address,text,function(request){postFilms(request);},fm);
	
	
	}
	
	function ajaxPut(address,data,responseHandler,format){
	var request= getRequestObject(); 
	console.log("01");
	request.onreadystatechange=
	
	function(){
		responseHandler(request);
		
		};
		console.log(data);
		console.log(format);
		request.open("PUT", address, true);
		request.setRequestHeader("Content-Type",format); //setting content-type to the format selected(application/text)
		request.send(data);
	}
	
	function putFilms(request){
		if((request.status==200)&&(request.readyState==4)){
			var data=request.responseText.toString();
			var splitter = data.split(/[\n\r]+/);
			var arraylist = new Array(data.length -1);
			
			
			for ( var i=1;i<data.length; i++){
				arraylist[i-1]=data[i].split("|");
			}
			var stringify = arraylist.toString();
				var replace = stringify.replace(/,/g , "");
			
			
			console.log(arraylist);
			console.log(splitter);
			
		}
		}
		function filmsPut(id,format){
			var split=id;
	var array=split.split("|");
	var id=array[0];
	console.log(id);
	var fm=format;
	var address="Restful";
	var title= prompt("Update title") //gets data
	var year= prompt("Update year") //gets data
	var director= prompt("Update director") //gets data
var stars= prompt("Update stars") //gets data
		var review= prompt("Update review") //gets data
	
	var text=id+"|"+title+"|"+year+"|"+director+"|"+stars+"|"+review;	
	
	
	console.log(text);
	ajaxPut(address,text,function(request){putFilms(request);},fm);
	
	
	}
	
	function ajaxDelete(address,data,responseHandler,format){
	var request= getRequestObject(); 
	console.log("01");
	request.onreadystatechange=
	
	function(){
		responseHandler(request);
		
		};
		console.log(data);
		console.log(format);
		request.open("DELETE", address, true);
		request.setRequestHeader("Content-Type",format); //setting content-type to the format selected(application/text)
		request.send(data);
	}
	
	function deleteFilms(request){
		if((request.status==200)&&(request.readyState==4)){
			var data=request.responseText.toString();
			var splitter = data.split(/[\n\r]+/);
			var arraylist = new Array(data.length -1);
			
			
			for ( var i=1;i<data.length; i++){
				arraylist[i-1]=data[i].split("|");
			}
			var stringify = arraylist.toString();
				var replace = stringify.replace(/,/g , "");
			
			
			console.log(arraylist);
			console.log(splitter);
			
		}
		}
		function filmsDelete(id,format){
			var split=id;
	var array=split.split("|");
	var id=array[0];
	console.log(id);
	var fm=format;
	var address="Restful";
	var text=id;	
	console.log(text);
	ajaxDelete(address,text,function(request){deleteFilms(request);},fm);
	
	
	}
	
	