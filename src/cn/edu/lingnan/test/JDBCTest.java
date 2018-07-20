package cn.edu.lingnan.test;

import java.util.Vector;

import cn.edu.lingnan.dao.CartDAO;
import cn.edu.lingnan.dao.CartItemDAO;
import cn.edu.lingnan.dao.GoodsDAO;
import cn.edu.lingnan.dao.SaleStatDAO;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.CartDTO;
import cn.edu.lingnan.dto.CartItemDTO;
import cn.edu.lingnan.dto.GoodsDTO;
import cn.edu.lingnan.dto.SaleStatDTO;
import cn.edu.lingnan.dto.UserDTO;

public class JDBCTest {

	public static void main(String[] args) {
		//查询user表
//		Vector<UserDTO> v = new Vector<UserDTO>();
//		UserDAO u = new UserDAO();
//		v = u.findAllUserInfo(1);
//		System.out.println("User table:");
//		for(UserDTO ud : v)
//			System.out.println(ud.getUid()+"\t"+ud.getUname()+"\t"+ud.getPassword()+"\t"+ud.getSuperuser()+"\t"+ud.getUstate());
		
		//查询goods表
//		Vector<GoodsDTO> v = new Vector<GoodsDTO>();
//		GoodsDAO g = new GoodsDAO();
//		v = g.findAllGoodsInfo(1);
//		System.out.println("Goods table:");
//		for(GoodsDTO gd : v)
//			System.out.println(gd.getGid()+"\t"+gd.getGname()+"\t"+gd.getPrice()+"\t"+gd.getCount());
//		
		
		//根据gid查询goods表中对应的gname
//		GoodsDAO g = new GoodsDAO();
//		System.out.println(g.findGnameByGid("g01"));
		
		//查询cart表
//		Vector<CartDTO> v = new Vector<CartDTO>();
//		CartDAO c = new CartDAO();
//		v = c.findAllCartInfo();
//		System.out.println("Cart table:");
//		for(CartDTO cd : v)
//			System.out.println(cd.getCid()+"\t"+cd.getUid()+"\t"+cd.getTotalPrice());
		
		//查询cartItem表
//		Vector<CartItemDTO> v = new Vector<CartItemDTO>();
//		CartItemDAO i = new CartItemDAO();
//		v = i.findAllCartItemInfo();
//		System.out.println("CartItem table:");
//		for(CartItemDTO id : v)
//			System.out.println(id.getCid()+"\t"+id.getGid()+"\t"+id.getNum());
		
		//根据cid查询cartItem表
//		Vector<CartItemDTO> v = new Vector<CartItemDTO>();
//		CartItemDAO i = new CartItemDAO();
//		v = i.findCartItemInfoByCid(5);
//		System.out.println("CartItem table:");
//		for(CartItemDTO id : v)
//			System.out.println(id.getCid()+"\t"+id.getGid()+"\t"+id.getNum());
		
		//检查该记录是否存在cartItem表
//		CartItemDAO i = new CartItemDAO();
//		System.out.println(i.findCartItemInfoByCidAndGidAndNum(9, "g06", 1));
		
		//根据cid查询cart表的totalPrice
//		CartDAO c = new CartDAO();
//		System.out.println(c.findTotalPriceByUid(7));
		
		//根据uid删除user表中的一条记录
//		UserDAO u = new UserDAO();
//		System.out.println(u.deleteUserById("u01"));
		
		//根据gid删除goods表中的一条记录
//		GoodsDAO g = new GoodsDAO();
//		System.out.println(g.deleteGoodsById("g01"));
		
		//根据cid删除cart表中的一条记录
//		CartDAO c = new CartDAO();
//		System.out.println(c.deleteCartById("c01"));
		
		//根据cid,gid 删除cartItem表中的一条记录
//		CartItemDAO i = new CartItemDAO();
//		System.out.println(i.deleteCartItemByCidAndGid("c01", "g01"));
		
		///根据cid,gid 删除cartItem表中的一条记录
//		UserDAO u = new UserDAO();
//		System.out.println(u.updateUserPasswordById("u01", "222"));
//		System.out.println(u.updateUserNameById("u01", "mandyyi"));
		
		//根据goods表中的id更改商品数量count和价格price
//		GoodsDAO g = new GoodsDAO();
//		System.out.println(g.updateGoodsCountById("g01", 23));
//		System.out.println(g.updateGoodsPriceById("g01", 11));
		
		//根据cart表中的cid更改uid（一条记录）
//		CartDAO c = new CartDAO();
//		System.out.println(c.updateCartUidByCid("c02", "u03"));

		
		//根据cartItem表中的cid,gid更改商品数量num
//		CartItemDAO i = new CartItemDAO();
//		System.out.println(i.UpdateCartItemNumByCidAndGid(17, "g05", 100));		
		
		//更改user表中一条完整的记录
//		UserDAO u = new UserDAO();
//		UserDTO ud = new UserDTO();
//		ud.setUid("u10");
//		ud.setUname("uname");
//		ud.setPassword("111");
//		ud.setSuperuser(4);
//		ud.setUstate(3);
//		System.out.println(u.updateUserInfoByID(ud));
		
		//查找登陆用户的id
//		UserDAO u = new UserDAO();
//		System.out.println(u.findUserByName("小明"));
		
		//向user表中插入一条完整的记录
//		UserDAO u = new UserDAO();
//		System.out.println(u.insertUserInfo("u07", "xiaoxiao", "2222"));
		
		//向goods表中插入一条记录
//		GoodsDAO g = new GoodsDAO();
//		System.out.println(g.insertGoodsInfo("g06", "gogo", 4, 3));
		
		//向cart表中插入一条完整的记录
//		CartDAO c = new CartDAO();
//		System.out.println(c.insertCartInfo("u01"));
		
		//查找cart表中的最后一条记录的cid
//		CartDAO c = new CartDAO();
//		System.out.println(c.findLastCartInfo(1));
		
		//向cartItem表中插入一条完整的记录
//		CartItemDAO i = new CartItemDAO();
//		System.out.println(i.insertCartItemInfo(3, "g05", 2));
		
		//删除saleStat表中一条记录（根据gid）
//		SaleStatDAO s = new SaleStatDAO();
//		System.out.println(s.deleteSaleStatById("g01"));
		
		//查询saleState表
//		Vector<SaleStatDTO> v = new Vector<SaleStatDTO>();
////		SaleStatDAO s = new SaleStatDAO();
//		v = s.findAllSaleStatInfo(1);
//		System.out.println("Goods saleStat:");
//		for(SaleStatDTO sd : v)
//			System.out.println(sd.getGid()+"\t"+sd.getSaleNum());
		
	}
}
