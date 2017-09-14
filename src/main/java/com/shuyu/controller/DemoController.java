package com.shuyu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuyu.entity.Demo;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @Api�����������࣬����Controller������
 * @ApiOperation������һ�����һ������������˵һ���ӿ�
 * @ApiParam��������������
 * @ApiModel���ö��������ղ���
 * @ApiProperty���ö�����ղ���ʱ�����������һ���ֶ�
 * @author Shuyu.Wang
 *
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoController {
	private Logger logger = LoggerFactory.getLogger(DemoController.class);

	/**
	 * ����ֱ��ʹ��@ResponseBody��ӦJSON
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getcount", method = RequestMethod.POST)
	@ApiOperation(value = "����-getCount", notes = "getCount����˵��")
	public ModelMap getCount(HttpServletRequest request, HttpServletResponse response) {
		logger.info(">>>>>>>> begin getCount >>>>>>>>");
		ModelMap map = new ModelMap();
		map.addAttribute("count", 158);

		// ��̨��ȡ�Ĺ��ʻ���Ϣ
		map.addAttribute("xstest", "����");
		return map;
	}

	/**
	 * ����ֱ��ʹ��@ResponseBody��ӦJSON
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiIgnore // ʹ�ø�ע��������API
	@ResponseBody
	@RequestMapping(value = "/jsonTest1", method = RequestMethod.POST)
	public ModelMap jsonTest(HttpServletRequest request, HttpServletResponse response) {
		ModelMap map = new ModelMap();
		map.addAttribute("hello", "���");
		map.addAttribute("veryGood", "�ܺ�");

		return map;
	}

	/**
	 * ����ֱ��ʹ��@ResponseBody��ӦJSON
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonTest3", method = RequestMethod.POST)
	public List<String> jsonTest3(HttpServletRequest request, HttpServletResponse response) {
		List<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("���");
		return list;
	}

	/**
	 * JSON����һ������<br/>
	 * ��Ajax Post Data��{"name":"����","content":"����"}��
	 * 
	 * @param version
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonTest2", method = RequestMethod.POST)
	public ModelMap jsonTest2(@RequestBody Demo demo) {
		logger.info("demoName��" + demo.getName());
		logger.info("demoContent��" + demo.getContent());
		ModelMap map = new ModelMap();
		map.addAttribute("result", "ok");
		return map;
	}

	/**
	 * ֱ�Ӷ�ȡURL����ֵ<br/>
	 * /demo/jsonTest6.do?name=Hello&content=World
	 * 
	 * @param demoName
	 * @param content
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonTest6", method = RequestMethod.POST)
	public ModelMap jsonTest6(@RequestParam("name") String demoName, @RequestParam String content) {
		logger.info("demoName��" + demoName);
		ModelMap map = new ModelMap();
		map.addAttribute("name", demoName + "AAA");
		map.addAttribute("content", content + "BBB");
		map.addAttribute("date", new java.util.Date());
		return map;
	}

	/**
	 * JSON����һ�����󣬽�RequestBody�Զ�ת��ΪJSONObject����<br/>
	 * ��Ajax Post Data��{"name":"����","content":"����"}��
	 * 
	 * ʹ��JSONObject��������� <dependency> <groupId>net.sf.json-lib</groupId>
	 * <artifactId>json-lib</artifactId> <version>2.4</version> <!--ָ��jdk�汾 -->
	 * <classifier>jdk15</classifier> </dependency>
	 * 
	 * @param version
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonTest5", method = RequestMethod.POST)
	public ModelMap jsonTest5(@RequestBody JSONObject jsonObject) {
		String name = jsonObject.getString("name");
		logger.info("demoName��" + name);
		ModelMap map = new ModelMap();
		map.addAttribute("demoName", name);
		return map;
	}

	/**
	 * ���� �����ΪJSON��ʽ�����ݵķ�ʽ HttpEntity<?> ResponseEntity<?>
	 * 
	 * @param u
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonTest4", method = RequestMethod.POST)
	public ResponseEntity<String> jsonTest4(HttpEntity<Demo> demo, HttpServletRequest request, HttpSession session) {
		// ��ȡHeaders����
		HttpHeaders headers = demo.getHeaders();

		// ��ȡ����
		String demoContent = demo.getBody().getContent();

		// ����ֱ��newһ������HttpHeaders headers = new HttpHeaders();��
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("MyHeaderName", "SHANHY");

		ResponseEntity<String> responseResult = new ResponseEntity<String>(demoContent, responseHeaders, HttpStatus.OK);
		return responseResult;
	}

}
