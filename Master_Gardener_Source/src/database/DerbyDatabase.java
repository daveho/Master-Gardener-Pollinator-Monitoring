package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import database.DBUtil;
import database.DerbyDatabase;
import database.InitialData;
import database.PersistenceException;
import database.DatabaseProvider;


import model.Account;
import model.Group;
import model.GroupMember;
import model.Post;
import model.Pair;
import model.Garden;
import model.County;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}

	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	private interface Query<ReturnType>{
		public ReturnType query(Connection conn) throws SQLException;
	}

	/* ----------------------------------------------Query Functions---------------------------------------------- */

	public int queryForLoginIdByUsername(final String username){
		try{
			return doQueryLoop(new Query<Integer>(){
				@Override
				public Integer query(Connection conn) throws SQLException{
					PreparedStatement stmt = null;
					ResultSet set = null;
					int loginId = -1;
					try{
						stmt = conn.prepareStatement(
								" SELECT login_id FROM accounts "
										+ " WHERE username = ?");
						stmt.setString(1, username);
						set = stmt.executeQuery();

						if(set.next()){
							loginId = set.getInt(1);
						}
					}finally{
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(set);
					}
					return loginId;
				}
			});
		}catch(SQLException e){
			System.out.println("queryForLoginIdByUsername: "+e.getMessage());
			return -1;
		}
	}

	//This can handle multiple Garden ID

	/**
	 *
	 * @param account_id
	 * @return Garden ID related to the Account ID
	 */
	public int getGardenIDByAccountID(final int account_id)
	{
		try {
			return doQueryLoop(new Query<Integer>() {
				@Override
				public Integer query(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet set = null;
					int garden_id = -1;
					try {
						stmt = conn.prepareStatement(
								" select garden_id " +
										"	from gardens, accounts"
										+ " where accounts.account_id = ?" +
										"	and gardens.account_id = accounts.account_id");
						stmt.setInt(1, account_id);
						set = stmt.executeQuery();

						// testing that a set was returned
						Boolean found = false;

						if (set.next()) {
							found = true;
							garden_id = set.getInt(1);
						}
						if(!found)
						{
							System.out.println("User ID <" + account_id + "> was not found in the accounts table.");
							System.out.println("Check the SQL code in the query, account_id may need to change.");
							System.out.println("May also need a many-to-many table to connect gardens <-> accounts.");
						}
					} finally {
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(set);
					}
					return garden_id;
				}
			});
		}
		catch (SQLException e) {
			System.out.println("Error in getGardenIDByAccountID: " + e.getMessage());
			return -1;
		}
	}

	/**
	 * 
	 * @param county_id - The identifier for a county. Use this with a drop-down box, most likely.
	 * @return
	 */
	public String getCountyByCountyID(final int county_id)
	{
		try {
			return doQueryLoop(new Query<String>() {
				@Override
				public String query(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet set = null;
					String county_name = "";
					try {
						stmt = conn.prepareStatement(
								" select county_name " +
										"	from counties"
										+ " where county_id = ?"
										);
						stmt.setInt(1, county_id);
						set = stmt.executeQuery();

						// testing that a set was returned
						Boolean found = false;

						if (set.next()) {
							found = true;
							county_name = set.getString(1);
						}
						if(!found)
						{
							System.out.println("County name <" + county_name + "> was not found in the counties table.");
						}
					} finally {
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(set);
					}
					return county_name;
				}
			});
		}
		catch (SQLException e) {
			return "Error in getCountyByCountyID: " + e.getMessage() + ".";
			//return -1;
		}
	}
	
	public String getCountyByStateName(final String state_name)
	{
		try {
			return doQueryLoop(new Query<String>() {
				@Override
				public String query(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet set = null;
					String county_name = "";
					try {
						stmt = conn.prepareStatement(
								" select county_name " +
										"	from counties"
										+ " where state_name = ?"
										);
						stmt.setString(1, state_name);
						set = stmt.executeQuery();

						// testing that a set was returned
						Boolean found = false;

						if (set.next()) {
							found = true;
							county_name = set.getString(1);
						}
						if(!found)
						{
							System.out.println("County name <" + county_name + "> was not found in the counties table.");

						}
					} finally {
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(set);
					}
					return county_name;
				}
			});
		}
		catch (SQLException e) {
			return "Error in getCountyByStateName: " + e.getMessage() + ".";
			//return -1;
		}
	}
	
	public String getUsernameByCounty(final String county_name)
	{
		try {
			return doQueryLoop(new Query<String>() {
				@Override
				public String query(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet set = null;
					String username = "";
					try {
						stmt = conn.prepareStatement(
								" select username " +
										"	from accounts, counties"
										+ " where county_name = ? "
										+ " and accounts.account_id = counties.account_id "
										);
						stmt.setString(1, county_name);
						set = stmt.executeQuery();

						// testing that a set was returned
						Boolean found = false;

						if (set.next()) {
							found = true;
							username = set.getString(1);
						}
						if(!found)
						{
							System.out.println("User <" + username + "> was not found attached to the counties table.");
							System.out.println("Check SQL code for counties.accounts_id, it may need to be counties.county_account_id");
							System.out.println("May also need a many-to-many table to connect accounts <-> counties.");
						}
					} finally {
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(set);
					}
					return county_name;
				}
			});
		}
		catch (SQLException e) {
			return "Error in getGardenIDByAccountID: " + e.getMessage() + ".";
			//return -1;
		}
	}
	
	
	/**
	 *
	 * @param garden_id
	 * @return Garden address of the specified Garden ID
	 */
	public String getGardenAddressByGardenID(final int garden_id)
	{
		try {
			return doQueryLoop(new Query<String>() {
				@Override
				public String query(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet set = null;
					String garden_address = "";
					try {
						stmt = conn.prepareStatement(
								" select garden_address " +
										"	from gardens"
										+ " where garden_id = ?"
										);
						stmt.setInt(1, garden_id);
						set = stmt.executeQuery();

						// testing that a set was returned
						Boolean found = false;

						if (set.next()) {
							found = true;
							garden_address = set.getString(1);
						}
						if(!found)
						{
							System.out.println("Garden Address <" + garden_address + "> was not found in the gardens table.");
						}
					} finally {
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(set);
					}
					return garden_address;
				}
			});
		}
		catch (SQLException e) {
			return "Error in getGardenIDByAccountID: " + e.getMessage() + ".";
			//return -1;
		}
	}

	/**
	 *
	 * @param county_id
	 * @return Usernames of people locked into a specific county
	 */
	// TODO: Check this, the sql logic may malfunction
	public String getUsernameByCountyID(final int county_id)
	{
		try {
			return doQueryLoop(new Query<String>() {
				@Override
				public String query(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet set = null;
					String username = "";
					try {
						stmt = conn.prepareStatement(
								" select username " +
										"	from accounts, counties"
										+ " where counties.county_id = ?" +
										" and counties.account_id = accounts.account_id"
						);
						stmt.setInt(1, county_id);
						set = stmt.executeQuery();

						// testing that a set was returned
						Boolean found = false;

						if (set.next()) {
							found = true;
							username = set.getString(1);
						}
						if(!found)
						{
							System.out.println("User <" + username + "> was not found related to the counties table.");
							System.out.println("Check the SQL code in the query, account_id may need to be county_account_id or something.");
							System.out.println("May also need a many-to-many table to connect accounts <-> counties.");
						}
					} finally {
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(set);
					}
					return username;
				}
			});
		}
		catch (SQLException e) {
			return "Error in getGardenIDByAccountID: " + e.getMessage() + ".";
			//return -1;
		}
	}

	public boolean updateAccountByUsername(final String username, final Account account){
		try{
			return doQueryLoop(new Query<Boolean>(){
				@Override
				public Boolean query(Connection conn)throws SQLException{
					if(verifyAccountExistsByUsername(conn,username))
						return updateAccountByUsername(conn, username,account);

					else
						return false;
				}
			});
		}catch(SQLException e){
			System.out.println("updateAccountByUsername: "+e.getMessage());
			return false;
		}
	}

	public List<Group> getGroupbyGroupName(final String name){
		return executeTransaction(new Transaction<List<Group>>() {
			public List<Group> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet set = null;



				try{
					stmt1 = conn.prepareStatement("select groups.group_id, groups.name, groups.description, groups.rating from groups where groups.name = ?");
					stmt1.setString(1, name);
					set = stmt1.executeQuery();


					List<Group> returnGroup = new ArrayList<Group>();
					Boolean found = false;

					while(set.next()) {
						found = true;
						Group group = new Group();

						loadGroup(group, set, 1);
						returnGroup.add(group);
					}
					if (!found) {
						System.out.println("<" + name + "> is not a group");
					}

					return returnGroup;
				}
				finally{
					DBUtil.closeQuietly(set);
					DBUtil.closeQuietly(stmt1);
				}

			}
		});

	}


	public List<Group> getGroupbyGroupID(final int ID){
		return executeTransaction(new Transaction<List<Group>>() {
			public List<Group> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet set = null;

				try{
					stmt1 = conn.prepareStatement(
							" select * from groups where groups.group_id = ?"
					);
					stmt1.setInt(1, ID);

					set = stmt1.executeQuery();

					List<Group> returnGroups = new ArrayList<Group>();
					//Boolean found = false;

					while(set.next()) {
						//found = true;
						Group group = new Group();

						loadGroup(group, set, 1);
						returnGroups.add(group);
					}

					//if (!found) {
					//System.out.println("<" + ID + "> is not in the database");
					//}
					return returnGroups;
				}	finally{
					DBUtil.closeQuietly(set);
					DBUtil.closeQuietly(stmt1);
				}



			}
		});
	}


	public List<Pair<Account, Post>> getPostsbyGroupID(final int ID) {
		return executeTransaction(new Transaction <List<Pair<Account, Post>>>() {
			public List<Pair<Account, Post>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet set = null;

				try{
					stmt1 = conn.prepareStatement(
							" select accounts.*, posts.* from "+
									" accounts, posts, groupMembers "+
									" where posts.group_id = ?  and accounts.account_id = groupmembers.account_id "+
									" and groupmembers.group_id = posts.group_id " +
									" and accounts.account_id = posts.account_id" +
									" order by posts.post_id DESC"
					);


					stmt1.setInt(1, ID);

					set = stmt1.executeQuery();

					List<Pair<Account, Post>> returnPosts = new ArrayList<Pair<Account, Post>>();
					//Boolean found = false;

					while(set.next()) {
						//found = true;
						Post post = new Post();
						Account account = new Account();
						loadAccount(account, set, 1);
						loadPost(post, set, 8);
						returnPosts.add(new Pair<Account, Post>(account, post));
					}

					//if (!found) {
					//System.out.println("<" + ID + "> is not in the database");
					//}
					return returnPosts;
				}	finally{
					DBUtil.closeQuietly(set);
					DBUtil.closeQuietly(stmt1);
				}



			}
		});
	}

	public List<Group> getGroupsLikeKeyword(final String keyword){
		return executeTransaction(new Transaction<List<Group>>() {
			@Override
			public List<Group> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet set = null;
				System.out.println("reached DB with keyword "+ keyword);
				try{

					stmt1 = conn.prepareStatement(
							"select groups.group_id, groups.name, groups.description, groups.rating from groups where groups.name like ? "
					);
					stmt1.setString(1, "%"+keyword+"%");

					set = stmt1.executeQuery();

					List<Group> returnGroups = new ArrayList<Group>();
					//Boolean found = false;

					while(set.next()) {
						//found = true;
						Group group = new Group();

						loadGroup(group, set, 1);
						returnGroups.add(group);
					}

					//if (!found) {
					//System.out.println("<" + keyword + "> not found as a valid group");
					//}
					return returnGroups;
				}	finally{
					DBUtil.closeQuietly(set);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}

	public List<Group> getGroupsByUser(final String user){
		return executeTransaction(new Transaction<List<Group>>() {
			@Override
			public List<Group> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet set = null;

				try{

					stmt1 = conn.prepareStatement(
							"select groups.group_id, groups.name, groups.description, groups.rating from groups, accounts, groupMembers where accounts.username = ? and accounts.account_id = groupMembers.account_id and groupMembers.group_id = groups.group_id"
					);
					stmt1.setString(1, user);

					set = stmt1.executeQuery();

					List<Group> returnGroups = new ArrayList<Group>();
					//Boolean found = false;

					while(set.next()) {
						//found = true;
						Group group = new Group();

						loadGroup(group, set, 1);
						returnGroups.add(group);
					}

					//if (!found) {
					//System.out.println("<" + user + "> is not in the database");
					//}
					return returnGroups;
				}	finally{
					DBUtil.closeQuietly(set);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}

	public String queryForPasswordByUsername(final String username){
		try{
			return doQueryLoop(new Query<String>(){
				@Override
				public String query(Connection conn) throws SQLException{
					String password = null;
					password = getPasswordByUsername(conn,username);
					return password;
				}
			});
		} catch(SQLException e){
			System.out.println("queryForPasswordByUsername: "+e.getMessage());
			return null;
		}
	}

	public Account queryForUserAccountByUsername(final String username){
		try{
			return doQueryLoop(new Query<Account>(){
				@Override
				public Account query(Connection conn) throws SQLException{
					Account account = null;
					if(verifyAccountExistsByUsername(conn, username)){
						account = getAccountFromUsername(conn,username);
					}
					return account;
				}
			});
		}catch(SQLException e){
			System.out.println("queryForUserAccountByUsername: "+e.getMessage());
			return null;
		}
	}

	public boolean insertNewAccountIntoDatabase(final Account account){
		try{
			return doQueryLoop(new Query<Boolean>(){
				@Override
				public Boolean query(Connection conn) throws SQLException{
					boolean success = false;
					if(!verifyAccountExistsByUsername(conn, account.getUsername())){
						if(insertAccountIntoAccounts(conn,account));
						success = true;
					}
					return success;
				}

			});
		}catch(SQLException e){
			System.out.println("insertNewAccountIntoDatabase: "+e.getMessage());
			return false;
		}
	}

	public boolean insertNewGroupIntoDatabase(final Group group){
		try{
			return doQueryLoop(new Query<Boolean>(){
				@Override
				public Boolean query(Connection conn) throws SQLException{
					boolean success = false;
					if(!verifyGroupExistsbyName(conn, group.getName())){
						if(insertGroupintoGroups(conn,group));
						success = true;
					}
					return success;
				}

			});
		}catch(SQLException e){
			System.out.println("insertNewGroupIntoDatabase: "+e.getMessage());
			return false;
		}
	}


	public boolean insertNewPostIntoDatabase(final Post post){
		try{
			return doQueryLoop(new Query<Boolean>(){
				@Override
				public Boolean query(Connection conn) throws SQLException{
					boolean success = false;
					if(insertPostintoPosts(conn,post)){
						success = true;
					}
					return success;
				}

			});
		}catch(SQLException e){
			System.out.println("insertNewPostIntoDatabase: "+e.getMessage());
			return false;
		}
	}

	public boolean insertNewGroupMemberIntoDatabase(final GroupMember groupMember){
		try{
			return doQueryLoop(new Query<Boolean>(){
				@Override
				public Boolean query(Connection conn) throws SQLException{
					boolean success = false;
					if(insertGroupMemberintoGroupMembers(conn, groupMember)){
						success = true;
					}
					return success;
				}

			});
		}catch(SQLException e){
			System.out.println("insertNewGroupMemberIntoDatabase: "+e.getMessage());
			return false;
		}
	}




	/*
	 * -----------------------HELPER METHODS FOR STREAMLINING SQL QUERIES----------------------------------------------------
	 */

	private boolean updateAccountByUsername(Connection conn, String username, Account account) throws SQLException{
		boolean success = false;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet set = null;
		try{
			stmt = conn.prepareStatement(
					"UPDATE accounts "
							+ " SET username = ?, password = ?, login_id = ?, name = ?, email = ?, description = ? "
							+ " WHERE username = ? ");
			stmt.setString(1, account.getUsername());
			stmt.setString(2, account.getPassword());
			stmt.setInt(3, account.getLoginId());
			stmt.setString(4, account.getName());
			stmt.setString(5, account.getEmail());
			stmt.setString(6, account.getDescription());
			stmt.setString(7, username);
			stmt.executeUpdate();

			//get user_id
			stmt2 = conn.prepareStatement(
					"SELECT account_id FROM accounts "
							+ " WHERE username = ?");
			stmt2.setString(1, account.getUsername());
			set = stmt2.executeQuery();


			success = true;

		}finally{
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(stmt2);
			DBUtil.closeQuietly(set);
		}
		return success;
	}

	private Account getAccountFromUsername(Connection conn, String username) throws SQLException{
		Account account = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try{
			stmt = conn.prepareStatement(
					" SELECT * FROM accounts "
							+" WHERE username=?");
			stmt.setString(1, username);


			set = stmt.executeQuery();
			set.next();

			account = new Account(username, username, 0, username, username, username);
			loadAccount(account, set, 1);

		}finally{
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(set);
		}
		return account;
	}

	/*private Account getAccountFromUserId(Connection conn, int userId) throws SQLException{
		Account account = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try{
			stmt = conn.prepareStatement(
					" SELECT * FROM accounts "
							+" WHERE user_id=?");
			stmt.setInt(1, userId);

			set = stmt.executeQuery();

		}finally{
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(set);
		}
		return account;
	}*/

	private String getPasswordByUsername(Connection conn,String username) throws SQLException{
		String password = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try{
			stmt = conn.prepareStatement(
					" SELECT password FROM accounts WHERE username=? ");
			stmt.setString(1,username);
			set = stmt.executeQuery();

			if(set.next()){
				password = set.getString(1);
			}
		}finally{
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(set);
		}
		return password;
	}

	private boolean insertAccountIntoAccounts(Connection conn, Account account) throws SQLException{
		boolean success = false;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet set = null;

		try{
			stmt1 = conn.prepareStatement(
					"INSERT INTO accounts (username, password, login_id, name, email, description) "
							+ " VALUES(?,?,?,?,?,?)");
			stmt1.setString(1, account.getUsername());
			stmt1.setString(2, account.getPassword());
			stmt1.setInt(3, account.getLoginId());
			stmt1.setString(4, account.getName());
			stmt1.setString(5, account.getEmail());
			stmt1.setString(6, account.getDescription());

			stmt1.executeUpdate();

			stmt2 = conn.prepareStatement(
					"SELECT account_id FROM accounts "
							+ " WHERE username = ?");
			stmt2.setString(1, account.getUsername());
			set = stmt2.executeQuery();

		}finally{
			DBUtil.closeQuietly(stmt1);
			DBUtil.closeQuietly(stmt2);
			DBUtil.closeQuietly(set);
		}
		return success;
	}

	private boolean insertGroupintoGroups(Connection conn, Group group) throws SQLException{
		boolean success = false;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet set = null;

		try{
			stmt1 = conn.prepareStatement(
					"INSERT INTO groups ( name, description, rating) "
							+ " VALUES(?,?,?)");
			stmt1.setString(1, group.getName());
			stmt1.setString(2, group.getDescription());
			stmt1.setInt(3, group.getRating());



			stmt1.executeUpdate();

			stmt2 = conn.prepareStatement(
					"SELECT group_id FROM groups "
							+ " WHERE name = ?");
			stmt2.setString(1, group.getName());
			set = stmt2.executeQuery();

		}finally{
			DBUtil.closeQuietly(stmt1);
			DBUtil.closeQuietly(stmt2);
			DBUtil.closeQuietly(set);
		}
		return success;
	}


	private boolean insertPostintoPosts(Connection conn, Post post) throws SQLException{
		boolean success = false;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet set = null;

		try{
			stmt1 = conn.prepareStatement(
					"INSERT INTO posts ( account_id, group_id, text) "
							+ " VALUES(?,?,?)");
			stmt1.setInt(1, post.getAccountId());
			stmt1.setInt(2, post.getGroupId());
			stmt1.setString(3, post.getText());



			stmt1.executeUpdate();

			stmt2 = conn.prepareStatement(
					"SELECT post_id FROM posts "
							+ " WHERE text = ?");
			stmt2.setString(1, post.getText());
			set = stmt2.executeQuery();

		}finally{
			DBUtil.closeQuietly(stmt1);
			DBUtil.closeQuietly(stmt2);
			DBUtil.closeQuietly(set);
		}
		return success;
	}

	private boolean insertGroupMemberintoGroupMembers(Connection conn, GroupMember groupMember) throws SQLException{
		boolean success = false;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet set = null;

		try{
			stmt1 = conn.prepareStatement(
					"INSERT INTO groupMembers (group_id, account_id) "
							+ " VALUES(?,?)");
			stmt1.setInt(1, groupMember.getGroupId());
			stmt1.setInt(2, groupMember.getAccountId());

			stmt1.executeUpdate();

			stmt2 = conn.prepareStatement(
					"SELECT member_id FROM groupMembers "
							+ " WHERE groupMembers.group_id = ? and groupMembers.account_id = ?");
			stmt2.setInt(1, groupMember.getGroupId());
			stmt2.setInt(2, groupMember.getAccountId());
			set = stmt2.executeQuery();
			if(set.next()){
				success = true;
			}

		}finally{
			DBUtil.closeQuietly(stmt1);
			DBUtil.closeQuietly(stmt2);
			DBUtil.closeQuietly(set);
		}
		return success;
	}

	private boolean verifyAccountExistsByUsername(Connection conn, String username) throws SQLException{
		boolean registered = false;
		PreparedStatement stmt = null;
		ResultSet set = null;

		try{
			stmt = conn.prepareStatement(
					"SELECT * from accounts WHERE username=? ");
			stmt.setString(1, username);
			set = stmt.executeQuery();
			if(set.next()){
				registered = true;
			}
		}finally{
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(set);
		}
		return registered;
	}

	private boolean verifyGroupExistsbyName(Connection conn, String name) throws SQLException{
		boolean registered = false;
		PreparedStatement stmt = null;
		ResultSet set = null;

		try{
			stmt = conn.prepareStatement("select * from groups where groups.name = ?");
			stmt.setString(1, name);
			set = stmt.executeQuery();
			if(set.next()){
				registered = true;
			}
		}finally{
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(set);
		}
		return registered;
	}

	/*private Account inflateAccount(ResultSet set, int index) throws SQLException{
		Account account = new Account(null, null, index, null, null, null);
		account.setUsername(set.getString(index++));
		account.setPassword(set.getString(index++));
		account.setLoginId(set.getInt(index++));
		account.setName(set.getString(index++));
		account.setEmail(set.getString(index++));
		account.setDescription(set.getString(index++));

		return account;
	}*/

	/* ---------------------------- ------------------------------*/

	private <ReturnType> ReturnType doQueryLoop(Query<ReturnType> query) throws SQLException{
		Connection conn = connect();

		ReturnType ret = null;
		int times = 0;
		boolean done = false;
		try{
			while(!done && times < MAX_ATTEMPTS){
				try{
					ret = query.query(conn);
					conn.commit();
					done = true;
				}catch(SQLException e){
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						times++;
					} else {
						throw e;
					}
				}
			}

			if (!done) {
				throw new SQLException("Query Failed, TIMEOUT. ");
			}
			return ret;
		}finally{
			DBUtil.closeQuietly(conn);
		}
	}

	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}

	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();

		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;

			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}

			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}

			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	public Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");

		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);

		return conn;
	}

	private void loadAccount(Account account, ResultSet resultSet, int index) throws SQLException {
		account.setUserId(resultSet.getInt(index++));
		account.setUsername(resultSet.getString(index++));
		account.setPassword(resultSet.getString(index++));
		account.setLoginId(resultSet.getInt(index++));
		account.setName(resultSet.getString(index++));
		account.setEmail(resultSet.getString(index++));
		account.setDescription(resultSet.getString(index++));
	}

	private void loadGroup(Group group, ResultSet resultSet, int index) throws SQLException {
		group.setGroupId(resultSet.getInt(index++));
		group.setName(resultSet.getString(index++));
		group.setDescription(resultSet.getString(index++));
		group.setRating(resultSet.getInt(index++));

	}

	/*private void loadGroupMember(GroupMember groupMember, ResultSet resultSet, int index) throws SQLException {
		groupMember.setMemberId(resultSet.getInt(index++));
		groupMember.setGroupId(resultSet.getInt(index++));
		groupMember.setAccountId(resultSet.getInt(index++));		
	}*/


	private void loadPost(Post post, ResultSet resultSet, int index) throws SQLException {
		post.setPostId(resultSet.getInt(index++));
		post.setAccountId(resultSet.getInt(index++));
		post.setGroupId(resultSet.getInt(index++));
		post.setText(resultSet.getString(index++));
	}

	public boolean createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {

				
				PreparedStatement stmt1 = null; // Accounts table
				PreparedStatement stmt2 = null; // Groups table
				PreparedStatement stmt3 = null; // Group members table
				PreparedStatement stmt4 = null; // Posts table
				PreparedStatement stmt5 = null; // Gardens table
				PreparedStatement stmt6 = null; // County table
				PreparedStatement stmt7 = null; // Pollinators Table
				PreparedStatement stmt8 = null; //Plant Stats Table
				PreparedStatement stmt9 = null; // Plant Strands Table
				PreparedStatement stmt10 = null; // Plants Table
				PreparedStatement stmt11 = null; // Pollinator Record Table
				PreparedStatement stmt12 = null; // Pollinator Data Table
				
				try {
					//CREATING USER
					stmt1 = conn.prepareStatement(
							"create table accounts (" +
									"	account_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1), " +
									"	username varchar(40)," +
									"	password varchar(40)," +
									"   login_id integer," +
									"	name varchar(20)," +
									"	email varchar(50)," +
									"	description varchar(180) " +
									")"
					);
					stmt1.executeUpdate(); // Accounts
					
					//CREATING GROUP
					stmt2 = conn.prepareStatement(
							"create table groups (" +
									"	group_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1), " +
									"	name varchar(70)," +
									"	description varchar(150)," +
									"   rating integer " +
									")"
					);
					stmt2.executeUpdate(); // Groups
					
					//CREATING GROUP MEMBERS
					stmt3 = conn.prepareStatement(
							"create table groupMembers (" +
									"	member_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1), " +
									"	group_id integer constraint member_group_id references groups, " +
									"	account_id integer constraint group_account_id references accounts " +
									")"
					);
					stmt3.executeUpdate(); // Group Members
					
					//CREATING POSTS
					
					stmt4 = conn.prepareStatement(
							"create table posts (" +
									"	post_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1), " +
									"	account_id integer constraint post_account_id references accounts, " +
									" group_id integer constraint post_group_id references groups, " +
									"   text varchar(500) " +
									")"
					);
					stmt4.executeUpdate(); // Posts
					
					// TODO: Check this
					// Create Gardens Table
					stmt5 = conn.prepareStatement(
							"create table gardens (" +
									"garden_id integer primary key " +
									"	generated always as identity (start with 1, increment by 1), " +
									"	garden_address varchar(30)," +
									"	account_id integer constraint garden_account_id references accounts " +
									")"
					);
					stmt5.executeUpdate(); // Gardens

					// TODO: Check this
					// Create Counties Table
					stmt6 = conn.prepareStatement(
					"create table counties(" +
							"county_id integer primary key " +
							"	generated always as identity (start with 1, increment by 1)," +
							"county_name varchar(30)," +
							"garden_id integer constraint county_garden_id references gardens," +
							"account_id integer constraint county_account_id references accounts" +
							")"
					);
					stmt6.executeUpdate(); // Counties
					
					// TODO: Check this
					// Create Pollinators Table
					stmt7 = conn.prepareStatement(
					"create table pollinators(" +
							"pollinator_id integer primary key " +
							"	generated always as identity (start with 1, increment by 1)," +
							"pollinator_name varchar(30)," +
							"pollinator_data varchar(30)," +
							"pollinator_visit_count integer" +
							")"
					);
					stmt7.executeUpdate(); // Pollinators
					
					// TODO: Check this
					// Plant Stats Table
					stmt8 = conn.prepareStatement(
					"create table plant_stats(" +
							"stats_id integer primary key " +
							"	generated always as identity (start with 1, increment by 1)," +
							"plant_height integer," +
							"plant_size integer," +
							"blooms_open varchar(15)," +
							"percent_coverage integer," +
							"vigor varchar(15)" +
							//"plant_strand varchar(30)" +
							")"
					);
					stmt8.executeUpdate(); // Plant Stats
					
					
					// TODO: Check this
					// Create Plant Strands Table
					stmt9 = conn.prepareStatement(
					"create table plant_strands(" +
							"strand_id integer primary key " +
							"	generated always as identity (start with 1, increment by 1)," +
							"strand_name varchar(30)," +
							"pollinator_id integer constraint strand_pollinator_id references pollinators," +
							"pollinator_visit_count integer constraint strand_pollinator_visit_count references pollinators," +
																	// ^ This is confusing, it means "Plant strand
																	// table_pollinator_visit_count"
							"stats_id integer constraint strand_stats_id references plant_stats"+
							")"
					);
					stmt9.executeUpdate(); // Plant Strands
					
					// TODO: Check this
					// Create Plants Table
					stmt10 = conn.prepareStatement(
					"create table plants(" +
							"plant_id integer primary key " +
							"	generated always as identity (start with 1, increment by 1)," +
							"plant_name varchar(30)," +
							"strand_id integer constraint plant_strand_id references plant_strands" +
							")"
					);
					stmt10.executeUpdate(); // Plants

					

					// TODO: Figure out what's going on with this table. 
					// In the schema, this does NOT reference other tables, which is weird.
					// Is this table supposed to take data and upload it, or reference data?
					// Pollinator Record Table
					stmt11 = conn.prepareStatement(
					"create table pollinator_records(" +
							"record_id integer primary key " +
							"	generated always as identity (start with 1, increment by 1)," +
							" week_number integer " +
							/*					^ don't forget comma if we uncomment this section
							" plant_id integer, " +
							" plant_strand_ID integer," +
							" pollinator_id integer, " +
							" uploader_id integer, " +
							*/
							")"
					);
					stmt11.executeUpdate(); // Pollinator Record
					
					// TODO: Check this
					// Plant Stats Table
					stmt12 = conn.prepareStatement(
					"create table pollinator_data(" +
							"pollinator_data_id integer primary key " +
							"	generated always as identity (start with 1, increment by 1)," +
							" record_id integer constraint data_record_id references pollinator_records," +
							" county_id integer constraint data_county_id references counties," +
							// TODO: Is this account_id needed?
							" account_id integer constraint data_account_id references accounts," +
							" date varchar(10)," +
							" time_start integer," +
							" time_stop integer," +
							" temperature integer," +
							" wind varchar(10)," +
							" cloud varchar(10)," +
							// TODO: What is this data column business? A reference? I know I made the schema
							// but golly
							" pollinator_data_column varchar(50) " +
							")"
					);
					stmt12.executeUpdate(); // Pollinator Data
										
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
					DBUtil.closeQuietly(stmt7);
					DBUtil.closeQuietly(stmt8);
					DBUtil.closeQuietly(stmt9);
					DBUtil.closeQuietly(stmt10);
					DBUtil.closeQuietly(stmt11);
					DBUtil.closeQuietly(stmt12);
					
				}
			}
		});
		return true;
	}

	public boolean dropTables(){
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;
				PreparedStatement stmt7 = null;
				PreparedStatement stmt8 = null;
				PreparedStatement stmt9 = null;
				PreparedStatement stmt10 = null;
				PreparedStatement stmt11 = null;
				PreparedStatement stmt12 = null;

				try{
					stmt1 = conn.prepareStatement("DROP TABLE groupMembers"); // Group Members
					stmt2 = conn.prepareStatement("DROP TABLE posts"); // Posts table
					stmt3 = conn.prepareStatement("DROP TABLE accounts"); // Accounts table
					stmt4 = conn.prepareStatement("DROP TABLE groups"); // Groups table
					stmt5 = conn.prepareStatement("DROP TABLE gardens"); // Gardens table
					stmt6 = conn.prepareStatement("DROP TABLE counties"); // Counties table
					stmt7 = conn.prepareStatement("DROP TABLE pollinators"); // Pollinators Table
					stmt8 = conn.prepareStatement("DROP TABLE plants"); // Plants Table
					stmt9 = conn.prepareStatement("DROP TABLE plant_strands"); // Plant Strands Table
					stmt10 = conn.prepareStatement("DROP TABLE plant_stats"); // Plant Stats Table
					stmt11 = conn.prepareStatement("DROP TABLE pollinator_records"); // Pollinator Record Table
					stmt12 = conn.prepareStatement("DROP TABLE pollinator_data"); // Pollinator Data Table


					// Order matters here.
					stmt12.executeUpdate(); // Pollinator Data
					stmt1.executeUpdate(); // Group Members
					stmt2.executeUpdate(); // Posts table
					stmt6.executeUpdate(); // Counties table
					stmt5.executeUpdate(); // Gardens table
					stmt3.executeUpdate(); // Accounts table
					stmt4.executeUpdate(); // Groups table
					stmt8.executeUpdate(); // Plants
					stmt9.executeUpdate(); // Plant Strands
					stmt10.executeUpdate(); // Plant stats
					stmt7.executeUpdate(); // Pollinators Table
					stmt11.executeUpdate(); // Pollinator Record




					conn.commit();
				}catch(SQLException e){
					System.out.println(e.getMessage());
					return false;
				}finally{
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
					DBUtil.closeQuietly(stmt7);
					DBUtil.closeQuietly(stmt8);
					DBUtil.closeQuietly(stmt9);
					DBUtil.closeQuietly(stmt10);
					DBUtil.closeQuietly(stmt11);
					DBUtil.closeQuietly(stmt12);

				}
				return true;
			}
		});
		return true;
	}


	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Account> accountList;
				List<Group> groupList;
				List<GroupMember> groupMemberList;
				List<Post> postList;
				List<Garden> gardenList;
				List<County> countyList;
				try {
					accountList = InitialData.getAccounts();
					groupList = InitialData.getGroups();
					groupMemberList = InitialData.getGroupMembers();
					postList = InitialData.getPosts();
					// TODO: Garden List
					// TODO: County List
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAccount = null;
				PreparedStatement insertGroup   = null;
				PreparedStatement insertGroupMember   = null;
				PreparedStatement insertPost   = null;

				try {
					// populate accounts table (accounts first, since account_id is foreign key in groupMembers table)
					insertAccount = conn.prepareStatement("insert into accounts (username, password, login_id, name, email, description) values (?, ?, ?, ?, ?, ?)");
					for (Account account : accountList) {
						//						insertAccount.setInt(1, account.getUserId());	// auto-generated primary key, don't insert this
						insertAccount.setString(1, account.getUsername());
						insertAccount.setString(2, account.getPassword());
						insertAccount.setInt(3, account.getLoginId());
						insertAccount.setString(4, account.getName());
						insertAccount.setString(5, account.getEmail());
						insertAccount.setString(6, account.getDescription());
						insertAccount.addBatch();
					}
					insertAccount.executeBatch();

					insertGroup = conn.prepareStatement("insert into groups (name, description, rating) values (?, ?, ?)");
					for (Group group : groupList) {
						//						insertGroup.setInt(1, group.getGroupId());		// auto-generated primary key, don't insert this
						insertGroup.setString(1, group.getName());
						insertGroup.setString(2, group.getDescription());
						insertGroup.setInt(3, group.getRating());
						insertGroup.addBatch();
					}
					insertGroup.executeBatch();

					insertGroupMember = conn.prepareStatement("insert into groupMembers (group_id, account_id) values (?, ?)");
					for (GroupMember groupMember : groupMemberList) {
						//						insertGroupMember.setInt(1, groupMember.getMemberId());		// auto-generated primary key, don't insert this
						insertGroupMember.setInt(1, groupMember.getGroupId());
						insertGroupMember.setInt(2, groupMember.getAccountId());
						insertGroupMember.addBatch();
					}
					insertGroupMember.executeBatch();

					insertPost = conn.prepareStatement("insert into posts (account_id, group_id, text) values (?, ?, ?)");
					for (Post post : postList) {
						//						insertPost.setInt(1, post.getPostId());		// auto-generated primary key, don't insert this
						insertPost.setInt(1, post.getAccountId());
						insertPost.setInt(2, post.getGroupId());
						insertPost.setString(3, post.getText());
						insertPost.addBatch();
					}
					insertPost.executeBatch();

					return true;
				} finally {
					DBUtil.closeQuietly(insertAccount);
					DBUtil.closeQuietly(insertGroup);
					DBUtil.closeQuietly(insertGroupMember);
					DBUtil.closeQuietly(insertPost);
				}
			}
		});
	}

	/*
	// The main method creates the database tables and loads the initial data.
		public static void main(String[] args) throws IOException {
			System.out.println("Creating tables...");
			DerbyDatabase db = new DerbyDatabase();
			db.createTables();

			System.out.println("Loading initial data...");
			db.loadInitialData();

			System.out.println("Success!");
		}
	 */


	public static void main(String[] args) throws SQLException {
		System.out.println("----Loading Database Driver---- ");
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();

		System.out.println("----Connecting to Database---- ");
		Connection conn = db.connect();

		System.out.println("(C)reate table or (D)rop tables: ");
		Scanner in = new Scanner(System.in);

		if(in.nextLine().toUpperCase().equals("C")){
			System.out.println("----Creating Tables---- ");
			if(db.createTables()){
				System.out.println("Loading initial data...");
				db.loadInitialData();
				System.out.println("----Successfully Created Tables---- ");
			}

			else{
				System.out.println("----Failed to Create Tables---- ");
			}
		}
		else{
			System.out.println("----Preparing to Drop Tables---- ");
			if(db.dropTables()){
				System.out.println("----Successfully Dropped Tables---- ");
			}
			else{
				System.out.println("----Failed To Drop Table---- ");
			}
		}
		in.close();
		DBUtil.closeQuietly(conn);
	}
}