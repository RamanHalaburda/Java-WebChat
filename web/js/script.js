function datetime() 
{
    var s;
    var now = new Date();
    var y = now.getFullYear();
    var d = now.getDate();
    var mon = now.getMonth();
    switch (mon)
    {
		case 0: s="января"; break;
		case 1: s="февраля"; break;
		case 2: s="марта"; break;
		case 3: s="апреля"; break;
		case 4: s="мае"; break;
		case 5: s="июня"; break;
		case 6: s="июля"; break;
		case 7: s="августа"; break;
		case 8: s="сентября"; break;
		case 9: s="октября"; break;
		case 10: s="ноября"; break;
		case 11: s="декабря"; break;
    }
    var date_result = d + " " + s + " " + y;
    document.getElementById("inputdate").value = date_result;

    var hours = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds();

	var time_result = "" + hours + ((minutes < 10) ? ":0" : ":") + minutes + ((seconds < 10) ? ":0" : ":") + seconds;
	document.getElementById("inputtime").value = time_result;
	setTimeout("datetime()",1000);
}