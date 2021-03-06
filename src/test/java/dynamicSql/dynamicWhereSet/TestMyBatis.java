package dynamicSql.dynamicWhereSet;

import dao.BlogMapper;
import domain.Author;
import domain.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class TestMyBatis {
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
    Reader resource;

    {
        try {
            resource = Resources.getResourceAsReader("dynamicSql/dynamicWhereSet/mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resource);

    @Test
    void test1(){
        try(SqlSession sqlSession=sqlSessionFactory.openSession()){
            BlogMapper blogMapper=sqlSession.getMapper(BlogMapper.class);

            List<Blog> blogList;
            blogList=blogMapper.selectBlogsByWhere(null,null);
            System.out.println(blogList);

            blogList=blogMapper.selectBlogsByWhere("content11",null);
            System.out.println(blogList);

            Author author=new Author();
            author.setName("li");
            blogList=blogMapper.selectBlogsByWhere(null,author);
            System.out.println(blogList);

            blogList=blogMapper.selectBlogsByTrimWhere(null,author);
            System.out.println(blogList);

//            只是演示用法，下面两句update语句实际上都没有改变数据库里的值
            blogMapper.updateBlogBySet(1,"content11",null,null);
            blogMapper.updateBlogByTrimSet(1,"content11",null,"zhang");
            sqlSession.commit();

            /*执行结果
[Blog{id=1, content='content11', authorId=1, authorName='zhang'}, Blog{id=2, content='content12', authorId=1, authorName='zhang'}, Blog{id=3, content='content2', authorId=2, authorName='wang'}, Blog{id=4, content='content3', authorId=3, authorName='li'}]
[Blog{id=1, content='content11', authorId=1, authorName='zhang'}]
[Blog{id=4, content='content3', authorId=3, authorName='li'}]
[Blog{id=4, content='content3', authorId=3, authorName='li'}]
* */
        }
    }
}
