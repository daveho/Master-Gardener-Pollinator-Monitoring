package controller;
import model.Group;

import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;


import database.DatabaseProvider;
import database.DerbyDatabase;
import database.IDatabase;
 
public class SearchController {

	private IDatabase database = null;

	public SearchController() {

		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		database = DatabaseProvider.getInstance();		
	}

	public List<Group> getGroupsLike(String keyword) throws SQLException {
		
		List<Group> groups = database.getGroupsLikeKeyword(keyword);
		if (groups.isEmpty()) {
			System.out.println("No groups found that match the keyphrase provided");
			return null;
		}
		else {
			for(int x = 0; x < groups.size(); x++){
				System.out.println("Matching groups:");
				System.out.println(groups.get(x).getName());
			}
			return groups;
		}			
	}

	public int getGroupIDbyGroupname(String name) {
		List<Group> group = database.getGroupbyGroupName(name);
		int groupID = 0;
		String groupName;
		groupID = group.get(0).getGroupId();
		groupName = group.get(0).getName();
		System.out.println("Redirecting to "+ groupName);
		return groupID;
	}
}