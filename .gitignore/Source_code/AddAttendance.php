<?php
$trimmedsemester = intval($_POST[semester]);
$trimmedstudent=intval($_POST[student]);

 print("<h1>Add New Attendance Record</h1>

  <form name='addattendance' action='./index.php' method='POST'>
  <br><br><br>
  <table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='300'>
   <tr class='header'>
	<th>Date</th>
	<th>Type</th>
   </tr>
   <tr class='even' valign='top'>
	<td><input type='text' name='attdate' $_POST[student]maxlength='10' size='15' /></td>
	<td>
	 <select name='type'>
	  <option value='tardy'>Tardy</option>
	  <option value='absent'>Absent</option>
	 </select>
	</td>
   </tr>
   </table>

   <br>

   <table cellpadding='0' border='0' align='center' width='300'>
   <tr>
   <td><input type='button' value='Add Attendance' onClick='document.addattendance.addattendance.value=1;document.addattendance.page2.value=30;document.addattendance.submit();'> <input type='button' value='Cancel' onClick='document.addattendance.page2.value=30;document.addattendance.submit();'></td>
   </tr>
   </table>

  <input type='hidden' name='addattendance' value='' />
  <input type='hidden' name='semester' value='$trimmedsemester' />
  <input type='hidden' name='student' value='$trimmedstudent' />
  <input type='hidden' name='page2' value='$page2'>
  <input type='hidden' name='date'>
  <input type='hidden' name='logout'>
  <input type='hidden' name='page' value='$page'>

 </form>

 <table width='520' border=0 cellspacing=0 cellpadding=0 height=1>
  <tr>
   <td valign='top'>
	&nbsp;
   </td>
  </tr>
 </table>
 ");
?>
