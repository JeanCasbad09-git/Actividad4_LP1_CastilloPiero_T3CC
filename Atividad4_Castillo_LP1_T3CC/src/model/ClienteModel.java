package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;
import entidad.TipoCliente;
import util.MySQLConexion;

public class ClienteModel {
	
	public List<Cliente> lisCliente(){
		ArrayList<Cliente> cli=new ArrayList<Cliente>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=MySQLConexion.getConexion();
			String sql="select c.*,tc.nombre from cliente c inner join tipo_cliente tc on c.idTipoCliente=tc.idTpoCliente";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			
			rs=pstm.executeQuery();
			Cliente objC=null;
			TipoCliente objTC=null;
			while(rs.next()) {
				objC=new Cliente();
				objC.setIdCli(rs.getString(1));
				objC.setNom(rs.getString(2));
				objC.setApe(rs.getString(3));
				objC.setDni(rs.getString(4));
				objC.setFecNac(rs.getDate(5));
				
				objTC=new TipoCliente();
				objTC.setIdTipCli(rs.getString(6));
				objTC.setNomTip(rs.getString(7));
				
				objC.setIdTipCli(objTC.getIdTipCli());
				objC.setNomTip(objTC.getNomTip());
				
				cli.add(objC);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return cli;
	}
	
	public List<Cliente> lisNomCliente(String nom){
		ArrayList<Cliente> cli=new ArrayList<Cliente>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			con=MySQLConexion.getConexion();
			String sql="select c.*,tc.nombre from cliente c inner join tipo_cliente tc on c.idTipoCliente=tc.idTpoCliente where c.nombres=?";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, nom);
			System.out.println("SQL-->"+pstm);
			
			rs=pstm.executeQuery();
			Cliente objC=null;
			TipoCliente objTC=null;
			while(rs.next()) {
				objC=new Cliente();
				objC.setIdCli(rs.getString(1));
				objC.setNom(rs.getString(2));
				objC.setApe(rs.getString(3));
				objC.setDni(rs.getString(4));
				objC.setFecNac(rs.getDate(5));
				
				objTC=new TipoCliente();
				objTC.setIdTipCli(rs.getString(6));
				objTC.setNomTip(rs.getString(7));
				
				objC.setIdTipCli(objTC.getIdTipCli());
				objC.setNomTip(objTC.getNomTip());
				
				cli.add(objC);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return cli;
	}
}
