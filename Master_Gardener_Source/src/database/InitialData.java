package database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Account;
import model.County;
import model.Garden;
import model.Group;
import model.GroupMember;
import model.PollinatorData;
import model.Post;

public class InitialData {
	public static List<Account> getAccounts() throws IOException {
		List<Account> accountList = new ArrayList<Account>();
		ReadCSV readAccounts = new ReadCSV("accounts.csv");
		try {
			// auto-generated primary key for accounts table
			Integer userId = 1;
			while (true) {
				List<String> tuple = readAccounts.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Account account = new Account(null, null, userId, null, null, null);
				account.setUserId(userId++);
				account.setUsername(i.next());
				account.setPassword(i.next());
				account.setLoginId(Integer.parseInt(i.next()));
				account.setName(i.next());
				account.setEmail(i.next());
				account.setDescription(i.next());
				accountList.add(account);
			}
			return accountList;
		} finally {
			readAccounts.close();
		}
	}
	
	public static List<Group> getGroups() throws IOException {
		List<Group> groupList = new ArrayList<Group>();
		ReadCSV readGroups = new ReadCSV("groups.csv");
		try {
			// auto-generated primary key for groups table
			Integer groupId = 1;
			while (true) {
				List<String> tuple = readGroups.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Group group = new Group(null, null, 0);
				group.setGroupId(groupId++);
				group.setName(i.next());
				group.setDescription(i.next());
				group.setRating(Integer.parseInt(i.next()));
				groupList.add(group);
			}
			return groupList;
		} finally {
			readGroups.close();
		}
	}
	
	public static List<GroupMember> getGroupMembers() throws IOException {
		List<GroupMember> groupMemberList = new ArrayList<GroupMember>();
		ReadCSV readGroupMembers = new ReadCSV("groupMembers.csv");
		try {
			// auto-generated primary key for group members table
			Integer memberId = 1;
			while (true) {
				List<String> tuple = readGroupMembers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				GroupMember groupMember = new GroupMember(0, 0);
				groupMember.setMemberId(memberId++);
				groupMember.setGroupId(Integer.parseInt(i.next()));
				groupMember.setAccountId(Integer.parseInt(i.next()));
				groupMemberList.add(groupMember);
			}
			return groupMemberList;
		} finally {
			readGroupMembers.close();
		}
	}
	
	public static List<Post> getPosts() throws IOException {
		List<Post> postList = new ArrayList<Post>();
		ReadCSV readPosts = new ReadCSV("posts.csv");
		try {
			// auto-generated primary key for posts table
			Integer postId = 1;
			while (true) {
				List<String> tuple = readPosts.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Post post = new Post();
				post.setPostId(postId++);
				post.setAccountId(Integer.parseInt(i.next()));
				post.setGroupId(Integer.parseInt(i.next()));
				post.setText(i.next());
				postList.add(post);
			}
			return postList;
		} finally {
			readPosts.close();
		}
	}
	
	// pollinater data stuff
	public static List<PollinatorData> getPollinatorData() throws IOException {
		List<PollinatorData> pollinatorList = new ArrayList<PollinatorData>();
		ReadCSV readPollinatorData = new ReadCSV("Pollinatordata.csv");
		try {
			// auto-generated primary key for posts table
			Integer WeekID = 1;
			while (true) {
				List<String> tuple = readPollinatorData.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				PollinatorData PollinatorData = new PollinatorData();
				PollinatorData.setWeekID(WeekID++);
				PollinatorData.setTimeStart(Integer.parseInt(i.next()));
				PollinatorData.setTimeStop(Integer.parseInt(i.next()));
				pollinatorList.add(PollinatorData);
			}
			return pollinatorList;
		} finally {
			readPollinatorData.close();
		}
	}
	
	public static List<County> getCounties() throws IOException {
		List<County> countyList = new ArrayList<County>();
		ReadCSV readCountyData = new ReadCSV("CountyData.csv");
		try {
			// auto-generated primary key for posts table
			Integer CountyID = 1;
			while (true) {
				List<String> tuple = readCountyData.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				County CountyData= new County();
				CountyData.setCountyID(CountyID++);
				CountyData.setCountyName(i.next());
				CountyData.setStateName(i.next());
				countyList.add(CountyData);
			}
			return countyList;
		} finally {
			readCountyData.close();
		}
	}
	
	public static List<Garden> getGarden() throws IOException {
		List<Garden> GardenList = new ArrayList<Garden>();
		ReadCSV readGardenData = new ReadCSV("GardenData.csv");
		try {
			// auto-generated primary key for posts table
			Integer GardenID = 1;
			while (true) {
				List<String> tuple = readGardenData.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Garden GardenData= new Garden();
				GardenData.setGardenID(GardenID++);
				GardenData.setGardenAddress(i.next());
				GardenData.setGardenContact(i.next());
				GardenList.add(GardenData);
			}
			return GardenList;
		} finally {
			readGardenData.close();
		}
	}
	
	
	
	
	
	
	
	
	

}
