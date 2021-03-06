package com.ublblog.dao;

import com.github.pagehelper.Page;
import com.ublblog.model.Article;
import com.ublblog.model.ArticleExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ArticleMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_article
	 * @mbggenerated  Sat Apr 30 17:26:36 CST 2016
	 */
	int countByExample(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_article
	 * @mbggenerated  Sat Apr 30 17:26:36 CST 2016
	 */
	int deleteByExample(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_article
	 * @mbggenerated  Sat Apr 30 17:26:36 CST 2016
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_article
	 * @mbggenerated  Sat Apr 30 17:26:36 CST 2016
	 */
	int insert(Article record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_article
	 * @mbggenerated  Sat Apr 30 17:26:36 CST 2016
	 */
	int insertSelective(Article record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_article
	 * @mbggenerated  Sat Apr 30 17:26:36 CST 2016
	 */
	List<Article> selectByExampleWithBLOBs(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_article
	 * @mbggenerated  Sat Apr 30 17:26:36 CST 2016
	 */
	List<Article> selectByExample(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_article
	 * @mbggenerated  Sat Apr 30 17:26:36 CST 2016
	 */
	Article selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_article
	 * @mbggenerated  Sat Apr 30 17:26:36 CST 2016
	 */
	int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_article
	 * @mbggenerated  Sat Apr 30 17:26:36 CST 2016
	 */
	int updateByExampleWithBLOBs(@Param("record") Article record, @Param("example") ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_article
	 * @mbggenerated  Sat Apr 30 17:26:36 CST 2016
	 */
	int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_article
	 * @mbggenerated  Sat Apr 30 17:26:36 CST 2016
	 */
	int updateByPrimaryKeySelective(Article record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_article
	 * @mbggenerated  Sat Apr 30 17:26:36 CST 2016
	 */
	int updateByPrimaryKeyWithBLOBs(Article record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_article
	 * @mbggenerated  Sat Apr 30 17:26:36 CST 2016
	 */
	int updateByPrimaryKey(Article record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_article
     *
     * @mbggenerated Thu Apr 28 20:46:34 CST 2016
     */

    Page<Article> selectArticlesByTagId(int tagId);
}