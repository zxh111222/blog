package io.github.zxh111222.blog.util;

import com.youbenzi.mdtool.tool.MDTool;

public class Markdown2HtmlUtil {
    public static void main(String[] args) {
        String markdown = """
                ## 回顾
                见上次课的笔记
                
                ## 自动化检查三个 og 开头的标签
                改进上次课写的工具类，增加优化提醒等...
                - 错误：叉叉
                - 警告：三角形
                
                ## 课后作业解答
                ### 思路
                放胆去想
                
                ### 实现
                把自己的想法变成代码
                """;
        String html = Markdown2HtmlUtil.toHtml(markdown);
        System.out.println(html);
    }

    public static String toHtml(String markdown) {

        return MDTool.markdown2Html(markdown);
    }
}
