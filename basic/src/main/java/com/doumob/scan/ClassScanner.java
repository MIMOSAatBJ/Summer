package com.doumob.scan;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

import sun.net.www.protocol.jar.JarURLConnection;

/**
 * class扫描
 * @author killer
 *
 */
public class ClassScanner {
	private Logger logger=Logger.getLogger(getClass());
	/**
	 * class文件后缀名
	 */
	private final String class_suffix=".class";
	private final String filetype="file";
	private final String jar="jar";
	
	private String _package;
	private String _path;
	private boolean recursive;
	
	/**
	 * @param _package 要扫描的包
	 * @param recursive 是否递归查找 
	 */
	private ClassScanner(String _package,boolean recursive) {
		this._package=_package;
		this._path=_package.replace(".", "\\");
		this.recursive=recursive;
	}
	
	public static Set<String> scan(String _package,boolean recursive){
		ClassScanner sc=new ClassScanner(_package, recursive);
		return sc.scan();
	}
	
	/**
	 * 返回以.class结尾的文件
	 * @author killer
	 *
	 */
	public  class ClassFileFiter implements FileFilter{
		public boolean accept(File file) {
			return (recursive && file.isDirectory())|| file.getName().endsWith(class_suffix);
		}
	}
	
	/**
	 * 扫描
	 * @return
	 */
	public Set<String> scan(){
		Set<String> set=new HashSet<String>();
		if(_package!=null&&!_package.isEmpty()){
			try {
				Enumeration<URL> dirs= Thread.currentThread().getContextClassLoader().getResources(_path);
				while (dirs.hasMoreElements()) {
					URL url = (URL) dirs.nextElement();
					String protocol = url.getProtocol();
					if(filetype.equalsIgnoreCase(protocol)){
						String basepath = URLDecoder.decode(url.getFile(), "UTF-8");
						set.addAll(findInFile(basepath));
					}else if(jar.equalsIgnoreCase(protocol)){
						set.addAll(findInJar(url,_path));
					}else{
						logger.error("不支持的扫描类型["+protocol+"]。");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("查找出错，原因:"+e.getClass().getName());
			}
		}
		return set;
	}
	
	/**
	 * @param url 地址
	 * @param path
	 * @return
	 */
	private Set<String> findInJar(URL url,String path){
		Set<String> classnames=new HashSet<String>();
		try {
			JarFile jf = ((JarURLConnection) url.openConnection()).getJarFile();
			Enumeration<JarEntry> entries = jf.entries();
			while (entries.hasMoreElements()) {
				JarEntry entry = (JarEntry) entries.nextElement();
				String name = entry.getName();
				if (name.charAt(0) == '/') {
					// 获取后面的字符串
					name = name.substring(1);
				}
				if (name.startsWith(path)) {
					// 如果可以迭代下去 并且是一个包
					if ((name.lastIndexOf('/') != -1) || recursive) {
						// 如果是一个.class文件 而且不是目录
						if (name.endsWith(class_suffix)&& !entry.isDirectory()) {
							// 去掉后面的".class" 获取真正的类名
							String className = name.substring(0, name.length() - class_suffix.length());
							classnames.add(className.replace("/", "."));
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("查找出错，原因:"+e.getClass().getName());
		}
		return classnames;
	}
	
	
	/**
	 * @param basepath 基本路径
	 * @param recursive 是否递归查找
	 * @return
	 */
	private Set<String> findInFile(String basepath){
		Set<String> classnames=new HashSet<String>();
		try {
			File dir = new File(basepath);  
			if (!dir.exists()|| !dir.isDirectory()) {
				logger.info(basepath+"不存在，或者不是一个目录。");
	            return classnames;  
	        }
			File[] dirfiles = dir.listFiles(new ClassFileFiter());
			for (File f: dirfiles) {
				if(f.isFile()){
					String className="";
					className = f.getPath().substring(f.getPath().indexOf(_path));
					className=className.substring(0, className.length()-class_suffix.length());
					classnames.add(className.replace("\\", "."));
				}
				if(f.isDirectory()){
					classnames.addAll(findInFile(f.getPath()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查找出错，原因:"+e.getClass().getName());
		}
		return classnames;
	}
}
