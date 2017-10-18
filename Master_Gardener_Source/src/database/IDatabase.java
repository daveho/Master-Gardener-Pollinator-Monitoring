package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.Pair;
import model.Account;
import model.Group;
import model.GroupMember;
import model.Post;

public interface IDatabase {
	public boolean createTables();
	public boolean dropTables();
	public Connection connect() throws SQLException;
	public void loadInitialData();
	public int queryForLoginIdByUsername(String username);
	public Account queryForUserAccountByUsername(String username);
	public boolean updateAccountByUsername(String username, Account account);
	public String queryForPasswordByUsername(String username);
	public List<Group> getGroupsByUser(String user);
	public boolean insertNewAccountIntoDatabase(Account newb);
	public boolean insertNewGroupIntoDatabase(Group group);
	public List<Group> getGroupsLikeKeyword(String keyword);
	public List<Group> getGroupbyGroupName(String name);
	public List<Group> getGroupbyGroupID(int iD);
	public  List<Pair<Account, Post>> getPostsbyGroupID(int iD);
	public boolean insertNewPostIntoDatabase(Post post);
	public boolean insertNewGroupMemberIntoDatabase(GroupMember groupMember);

	// Master Gardener Program methods
	public int getGardenIDByAccountID(final int account_ID);
	public String getCountyByCountyID(final int county_id);
	public String getCountyByStateName(final String state_name);
	public String getUsernameByCounty(final String county_name);
	public String getGardenAddressByGardenID(final int garden_id);
	public String getUsernameByCountyID(final int county_id);
	
	
}
