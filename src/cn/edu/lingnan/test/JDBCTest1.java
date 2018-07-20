//package cn.edu.lingnan.test;
//
//import java.util.Scanner;
//import java.util.Vector;
//
//import cn.edu.lingnan.dao.CartDAO;
//import cn.edu.lingnan.dao.CartItemDAO;
//import cn.edu.lingnan.dao.GoodsDAO;
//import cn.edu.lingnan.dao.UserDAO;
//import cn.edu.lingnan.dto.CartDTO;
//import cn.edu.lingnan.dto.CartItemDTO;
//import cn.edu.lingnan.dto.GoodsDTO;
//import cn.edu.lingnan.dto.UserDTO;
//
//public class JDBCTest1 {
//	public void MainMenu(){
//		JDBCTest1 t = new JDBCTest1();
//		System.out.println("----------------------------");
//		System.out.println("	主菜单");
//		System.out.println("----------------------------");
//		System.out.println("	1.查询");
//		System.out.println("	2.删除");
//		System.out.println("	3.更新");
//		System.out.println("	4.插入");
//		System.out.println("	5.退出");
//		System.out.println("----------------------------");
//		System.out.print("请选择：");		
//		Scanner reader = new Scanner(System.in);		
//		Integer choose = null;		
//		choose = reader.nextInt();
//		switch(choose){ 
//		case 1: t.QMenu();
//				break;
//		case 2: t.DMenu();
//				break;
//		case 3: t.UMenu();
//				break;
//		case 4: t.IMenu();
//				break;
//		case 5: System.out.println("退出成功");
//				break;
//		}
//		reader.close();
//	}
//	
//	public void QMenu1(){
//		System.out.println("---------------------------");
//		System.out.println("	查询子菜单");
//		System.out.println("---------------------------");
//		System.out.println("	1.查询user表");
//		System.out.println("	2.查询goods表");
//		System.out.println("	3.查询cart表");
//		System.out.println("	4.查询cartItem表");
//		System.out.println("	5.返回主菜单");
//		System.out.println("---------------------------");
//		System.out.print("请选择：");		
//	}
//	
//	public void QMenu(){		
//		JDBCTest1 t = new JDBCTest1();
//		t.QMenu1();
//		Scanner reader = new Scanner(System.in);		
//		Integer choose;	
//		while(true){
//			choose = reader.nextInt();
//			switch(choose){ 
//			case 1: 
//				Vector<UserDTO> v = new Vector<UserDTO>();
//				UserDAO u = new UserDAO();
//				v = u.findAllUserInfo(1);
//				System.out.println("================================");
//				System.out.println("User table:");
//				for(UserDTO ud : v)
//					System.out.println(ud.getUid()+"\t"+ud.getUname()+"\t"+ud.getPassword()+"\t"+ud.getSuperuser());
//				System.out.println("================================");
//				t.QMenu1();
//				break;			
//			case 2:
//				Vector<GoodsDTO> v1 = new Vector<GoodsDTO>();
//				GoodsDAO g = new GoodsDAO();
//				v1 = g.findAllGoodsInfo(1);
//				System.out.println("================================");
//				System.out.println("Goods table:");
//				for(GoodsDTO gd : v1)
//					System.out.println(gd.getGid()+"\t"+gd.getGname()+"\t"+gd.getPrice()+"\t"+gd.getCount());
//				System.out.println("================================");
//				t.QMenu1();
//				break;		
//			case 3:
//				Vector<CartDTO> v3 = new Vector<CartDTO>();
//				CartDAO c = new CartDAO();
//				v3 = c.findAllCartInfo();
//				System.out.println("========================");
//				System.out.println("Cart table:");
//				for(CartDTO cd : v3)
//					System.out.println(cd.getCid()+"\t"+cd.getUid()+"\t"+cd.getTotalPrice());
//				System.out.println("========================");
//				t.QMenu1();
//				break;		
//			case 4: 
//				Vector<CartItemDTO> v2 = new Vector<CartItemDTO>();
//				CartItemDAO i = new CartItemDAO();
//				v2 = i.findAllCartItemInfo();
//				System.out.println("==========================");
//				System.out.println("CartItem table:");
//				for(CartItemDTO id : v2)
//					System.out.println(id.getCid()+"\t"+id.getGid()+"\t"+id.getNum());
//				System.out.println("==========================");
//				t.QMenu1();
//				break;	
//			case 5: 
//				t.MainMenu();
//				break;
//			}
//		}
//			
//	}
//	
//	public void DMenu1(){
//		System.out.println("---------------------------------------------------");
//		System.out.println("	删除子菜单");
//		System.out.println("---------------------------------------------------");
//		System.out.println("	1.根据uid删除user表中的一条记录");
//		System.out.println("	2.根据gid删除goods表中的一条记录");
//		System.out.println("	3.根据cid删除cart表中的一条记录");
//		System.out.println("	4.根据cid,gid删除cartItem表中的一条记录");
//		System.out.println("	5.返回主菜单");
//		System.out.println("---------------------------------------------------");
//		System.out.print("请选择：");		
//	}
//	
//	public void DMenu(){		
//		JDBCTest1 t = new JDBCTest1();	
//		t.DMenu1();	
//		Scanner reader = new Scanner(System.in);		
//		Integer choose = null;		
//		String id, cid, gid;
//		boolean flag = false;
//		UserDAO u = new UserDAO();
//		GoodsDAO g = new GoodsDAO();
//		CartDAO c = new CartDAO();
//		CartItemDAO i = new CartItemDAO();
//		while(true){
//			choose = reader.nextInt();
//			switch(choose){ 
//			case 1: 
//				System.out.println("请输入要删除记录的uid：");
//				id = reader.next();			
//				flag = u.deleteUserById(id);
//				if(flag)
//					System.out.println("删除成功");
//				else
//					System.out.println("删除失败");
//					t.DMenu1();
//				break;			
//			case 2:				
//				System.out.println("请输入要删除记录的gid：");
//				id = reader.next();			
//				flag = g.deleteGoodsById(id);
//				if(flag)
//					System.out.println("删除成功");
//				else
//					System.out.println("删除失败");
//				t.DMenu1();
//				break;			
//			case 3:
//				System.out.println("请输入要删除记录的cid：");
//				id = reader.next();			
//				flag = c.deleteCartById(id);
//				if(flag)
//					System.out.println("删除成功");
//				else
//					System.out.println("删除失败");
//				t.DMenu1();
//				break;	
//			case 4:
//				System.out.println("请输入要删除记录的cid,gid：");
//				cid = reader.next();
//				gid = reader.next();
//				flag = i.deleteCartItemByCidAndGid(cid, gid);
//				if(flag)
//					System.out.println("删除成功");
//				else
//					System.out.println("删除失败");
//				t.DMenu1();
//				break;	
//			case 5: t.MainMenu();
//					break;	
//			}	
//		}	
//	}
//	
//	public void UMenu1(){
//		System.out.println("-----------------------------------------------------------");
//		System.out.println("	更新子菜单");
//		System.out.println("-----------------------------------------------------------");
//		System.out.println("	1.根据user表中的uid更改password");
//		System.out.println("	2.根据user表中的uid更改name");
////		System.out.println("	3./根据goods表中的gid更改商品余量count");
////		System.out.println("	4./根据goods表中的gid更改商品价格price");
//		System.out.println("	3.根据cart表中的cid更改用户编号uid");
//		System.out.println("	4.根据cartItem表中的cid, gid更改商品数量num..");
//		System.out.println("	5.返回主菜单");
//		System.out.println("-----------------------------------------------------------");
//		System.out.print("请选择：");	
//	}
//	
//	public void UMenu(){		
//		JDBCTest1 t = new JDBCTest1();	
//		t.UMenu1();
//		Scanner reader = new Scanner(System.in);		
//		Integer choose, num;	
////		Integer count, price;
//		boolean flag = false;
//		String id, password, name, cid, uid, gid;
//		UserDAO u = new UserDAO();
////		GoodsDAO g = new GoodsDAO();
//		CartDAO c = new CartDAO();
//		CartItemDAO i = new CartItemDAO();
//		while(true){
//			choose = reader.nextInt();
//			switch(choose){ 
//			case 1:
//				System.out.println("请输入要更新记录的uid和需要更改的password：");
//				id = reader.next();	
//				password = reader.next();
//				flag = u.updateUserPasswordById(id, password);
//				if(flag)
//					System.out.println("更新成功");
//				else
//					System.out.println("更新失败");
//				t.UMenu1();
//				break;
//			case 2:
//				System.out.println("请输入要更新记录的uid和需要更改的name：");
//				id = reader.next();		
//				name = reader.next();	
//				flag = u.updateUserNameById(id, name);
//				if(flag)
//					System.out.println("更新成功");
//				else
//					System.out.println("更新失败");
//				t.UMenu1();
//				break;
////			case 3:
////				System.out.println("请输入要更新记录的gid和需要更改商品余量count：");
////				id = reader.next();		
////				count = reader.nextInt();	
////				flag = g.updateGoodsCountById(id, count);
////				if(flag)
////					System.out.println("更新成功");
////				else
////					System.out.println("更新失败");
////				t.UMenu1();
////				break;
////			case 4:
////				System.out.println("请输入要更新记录的gid和需要更改的price：");
////				id = reader.next();		
////				price = reader.nextInt();	
////				flag = g.updateGoodsPriceById(id, price);
////				if(flag)
////					System.out.println("更新成功");
////				else
////					System.out.println("更新失败");
////				t.UMenu1();
////				break;
//			case 3:
//				System.out.println("请输入要更新记录的cid和需要更改的用户编号uid：");
//				cid = reader.next();		
//				uid = reader.next();	
//				flag = c.updateCartUidByCid(cid, uid);
//				if(flag)
//					System.out.println("更新成功");
//				else
//					System.out.println("更新失败");
//				t.UMenu1();
//				break;
//			case 4: 
//				System.out.println("请输入要更新记录的cid,gid和需要更改的商品数量num：");
//				cid = reader.next();		
//				gid = reader.next();
//				num = reader.nextInt();
//				flag = i.UpdateCartItemNumByCidAndGid(cid, gid, num);
//				if(flag)
//					System.out.println("更新成功");
//				else
//					System.out.println("更新失败");
//				t.UMenu1();
//				break;
//			case 5: t.MainMenu();
//					break;
//			}
//		}	
//	}
//	
//	public void IMenu(){		
//		JDBCTest1 t = new JDBCTest1();	
//		System.out.println("-------------------------------");
//		System.out.println("	插入子菜单");
//		System.out.println("-------------------------------");
//		System.out.println("	1.向user插入一条记录");
//		System.out.println("	2.向goods插入一条记录");
//		System.out.println("	3.向cart插入一条记录");
//		System.out.println("	4.向cartItem插入一条记录..");
//		System.out.println("	5.返回主菜单");
//		System.out.println("-------------------------------");
//		System.out.print("请选择：");		
//		Scanner reader = new Scanner(System.in);		
//		Integer choose, price, count, num, totalPrice;
//		String cid, uid, gid, name, password;
//		boolean flag = false;
//		UserDAO u = new UserDAO();
//		GoodsDAO g = new GoodsDAO();
//		CartDAO c = new CartDAO();
//		CartItemDAO i = new CartItemDAO();
//		choose = reader.nextInt();
//		switch(choose){ 
////		case 1: 
////			System.out.println("请输入插入user表中的一条记录（uid, uname, password）:");
////			uid = reader.next();
////			name = reader.next();
////			password = reader.next();
////			flag = u.insertUserInfo(uid, name, password);
////			if(flag)
////				System.out.println("插入成功");
////			else
////				System.out.println("插入失败");
////			t.IMenu();
////			break;
//		case 2:
//			System.out.println("请输入插入goods表中的一条记录（gid, gname, price, count）:");
//			gid = reader.next();
//			name = reader.next();
//			price = reader.nextInt();
//			count = reader.nextInt();
////			flag = g.insertGoodsInfo(gid, name, price, count);
//			if(flag)
//				System.out.println("插入成功");
//			else
//				System.out.println("插入失败");
//			t.IMenu();
//			break;
//		case 3:
//			System.out.println("请输入插入cart表中的一条记录（cid, uid）:");
//			cid = reader.next();
//			uid = reader.next();
//			flag = c.insertCartInfo(cid, uid);
//			if(flag)
//				System.out.println("插入成功");
//			else
//				System.out.println("插入失败");
//			t.IMenu();
//			break;
//		case 4:
//			System.out.println("请输入插入cartItem表中的一条记录（cid, gid, num）:");
//			cid = reader.next();
//			gid = reader.next();
//			num = reader.nextInt();
//			flag = i.insertCartItemInfo(cid, gid, num);
//			if(flag)
//				System.out.println("插入成功");
//			else
//				System.out.println("插入失败");
//			t.IMenu();
//			break;
//		case 5: 
//			t.MainMenu();
//			break;
//		}	
//	}
//	
//	public static void main(String[] args){
//		JDBCTest1 t = new JDBCTest1();
//		t.MainMenu();				
//	}
//}
