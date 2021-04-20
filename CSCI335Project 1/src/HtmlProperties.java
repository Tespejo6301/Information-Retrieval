// class saves the file name and all the content of the file
public class HtmlProperties {
private String fileName;
private StringBuilder content = new StringBuilder();

public String getName()
{
	return fileName;
}
public StringBuilder getContent()
{
	return content;
}
public void setName(String n)
{
	n = n.replace("html/", "");
	this.fileName = n;
	
}
public void setContent(String input)
{
	content.append(input);
}	

	

}
