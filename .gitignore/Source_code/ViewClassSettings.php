<?php
 $trimmeselectclass=intval($_POST['selectclass']);
 // Update the grading Scale //
 if($_POST['update'] == 1)
 {
  $query = mysql_query("UPDATE courses SET aperc = '$_POST[aperc]', bperc = '$_POST[bperc]', cperc = '$_POST[cperc]', dperc = '$_POST[dperc]', fperc = '$_POST[fperc]' WHERE courseid = ' $trimmeselectclass'") or die("ClassSettings.php: Unable to update the grading scale - ".mysql_error());
 }

 $query = mysql_query("SELECT aperc, bperc, cperc, dperc, coursename FROM courses WHERE courseid =  $trimmeselectclass") or die("ClassInfo.php: Unable to get the class information - ".mysql_error());
 $info = mysql_fetch_row($query);

   print(" <h1>Class Settings</h1>
 <br><br>
 <form name='classes' action='./index.php' method='POST'>
 <table align='center' width='500' cellspacing='0' cellpadding='0' border='0'>
 <tr>
 <td>
  <table cellspacing='0' width='500' cellpadding='5' class='dynamiclist' align='center'>
   <tr class='header'>
	<th colspan='5' align='center'><h2>$info[5]</h2></th>
   </tr>
   <tr class='header' align='center'>
	<th>A Percent</th>
	<th>B Percent</th>
	<th>C Percent</th>
	<th>D Percent</th>
   </tr>
   <tr class='even'>
	<td>$info[0]</td>
	<td>$info[1]</td>
	<td>$info[2]</td>
	<td>$info[3]</td>
   </tr>
   ");

 print("</table>
   <input type='hidden' name='page2' value='$page2' />
   <input type='hidden' name='logout' />
   <input type='hidden' name='page' value='$page' />
   <input type='hidden' name='selectclass' value=' $trimmeselectclass' />
   <input type='hidden' name='update' />
   </form>
 </td>
 </tr>
 </table>
");
?>
