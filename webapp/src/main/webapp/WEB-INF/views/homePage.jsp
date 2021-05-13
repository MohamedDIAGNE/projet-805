<%@page import="java.util.List"%>
<%@page import="com.mongodb.DBObject"%>
<%@page import="org.bson.BSONObject "%>
<html>
<body>
<h2>Bonjour</h2>
<a href="/webapp/AddActivityServlet">Enregistrer activité</a> 

<h2>Activites</h2>
	<!--  <table">
			<thead>
					<tr>
						<th>id</th>
						<th>nom activite</th>
						<th>date</th>
					</tr>			   
			</thead>
			<tbody>
				<tr>
				 < %
					List<DBObject> attribut = (List<DBObject>)request.getAttribute("activites");         
					for( DBObject att : attribut   )
				    {
				  % >
				      
				          <td>
				             < %=att.get("_id")%>
				          </td>
				          <td>
				             < %=att.get("nomactivite")%>
				           </td>
				           <td>
				             < %=att.get("date")%>
				           </td>
				  < %  
				     }      
				  %>
				   </tr>

			</tbody>
	</table>-->
	<%
	List<DBObject> attribut = (List<DBObject>)request.getAttribute("activites");         
		for( DBObject att : attribut   )
		{
	%>
		<h5><%=att.get("_id")%> <%=att.get("nomactivite")%> <%=att.get("date")%></h5>
	<%  
		}      
	%>

</body>
</html>
