/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50711
Source Host           : 127.0.0.1:3306
Source Database       : mboot

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-03-15 18:30:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` mediumtext NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` datetime NOT NULL,
  `read_sum` int(11) NOT NULL DEFAULT '0',
  `title` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('8', '<p>原标题：李克强：为“餐桌上的安全”提供法治保障</p><p>　　“这次修改通过的《条例》有一个很大进步，就是把原来三个部门的职能合并到了一起。这对破解‘几龙治水’的困局提供了法治基础。”李克强在2月8日的国务院常务会议上说。</p><p>　　总理所说的《条例》，是当天会议通过的《农药管理条例（修订草案）》。该《条例》明确要求严格全过程管理，将原由多部门负责的农药生产管理职责统一划归农业部门，解决重复监管、监管盲区并存等问题，并对农药生产经营实行许可制，建立进销货查验、质量检验和废弃物回收等制度，鼓励减少农药使用量，加强剧毒、高毒农药监管。</p><p>　　“我们过去常常形容有些工作是‘几龙治水’，各自为政、相互掣肘；但有时也存在另一种情形，就是职能交叉造成管理分散，看起来好像几个部门共同管理，但最后反而没有一个部门认真管理。”李克强说。</p><p>　　总理随即对此次《条例》修订有关职能整合给予充分肯定。他说，这就让农业部门真正变得“有职有权”。这将会对加强农药管理，切实保障农产品质量安全起到法治保障作用。</p><p>　　此次修改的《条例》同时明确，生产经营者要对农药安全和有效性负责，要求健全质量管理制度，及时召回有严重危害或较大风险的农药。同时加大处罚力度。对无证生产经营、制售假冒伪劣农药等违法行为在原有处罚措施外，通过提高罚款额度、列入“黑名单”等加大惩戒。</p><p>　　李克强说：“要进一步严格监管，从源头上根治农药残留物，为‘餐桌上的安全’提供法治保障。”（李之南）</p><p></p><p>责任编辑：隗俊</p><p><br></p>', '2017-02-12 19:16:43', '2017-02-12 19:16:43', '2', '李克强：为“餐桌上的安全”提供法治保障');
INSERT INTO `t_article` VALUES ('9', '<p>原标题：港媒称特朗普初步确立对华政策：中美维持斗而不破</p><p><img src=\"http://n.sinaimg.cn/news/transform/20170212/F40N-fyamkra7004913.jpg\" alt=\"美国总统特朗普 新华社记者殷博古摄\">美国总统特朗普 新华社记者殷博古摄</p><p>　　参考消息网2月12日报道 港媒称，中美领导人通电话引发关注，中美关系走向以及特朗普对华政策是否初步成形也引起外界猜测。</p><p>　　据香港《经济日报》网站2月10日报道，有日本媒体分析认为，特朗普最近对华举动可能是受到前美国国务卿基辛格等人的建言。</p><p>　　报道称，特朗普这两天对华的举动引发外界不同解读。《日本经济新闻》网站认为，此次特朗普的举动被认为是接受了“知华派”前美国国务卿基辛格等的建言，但估计仍会坚持对华强硬路线。</p><p>　　香港《经济日报》网站2月10日刊登题为《特朗普对华政策成形 中美继续斗而不破》的报道称，特朗普打破了自当选以来冷待中国的态度，显而易见在权衡轻重后，已初步确立对华的政策，也就是延续过去中美斗而不破的局面。</p><p>　　报道称，特朗普当选后，其团队缺乏资深的外交人才，不断发出挑战中国的言论，部分更踩入中国的底线，尤其是在“一中”问题上。特朗普与蔡英文通电话，并多次发表以“一中”作为贸易谈判的筹码，这些言行均涉及到中国的国家核心利益。</p><p>　　然而，经过多月来，不少美国资深外交家、智库学者均提出，挑战“一中”是极其危险的事，甚至共和党内也有不少人对特朗普提出质疑。2月7日，一个有分量的报告提出，挑战“一中”极其危险。如今特朗普称，美国政府坚持奉行“一个中国”政策，显而易见特朗普在听取各方意见后，已初步确立了对华政策。</p><p>　　报道称，正如上述，特朗普接纳专家意见，不再挑战“一中”，但仍对中国维持强硬态度，未来中美在贸易、汇率以至地缘政治方面仍可能存在摩擦。但特朗普本是商人，因此，他竞选提出“美国优先”。如果与中国陷入全面贸易战，美国经济受损、失业率上升，显然并不符合美国利益。可预见的，中美仍将维持过往斗而不破的局面，双方仍会在谈判中争取最佳平衡点。</p><p>　　这次中美往来交手，北京展现了老练的外交手腕。首先，中国对特朗普挑战中国国家核心利益显示绝无退让的姿态。事实上，中国对于“一中”、南海等问题，与美国并无任何谈判空间。</p><p>　　报道称，但是，中国也多方寻求与特朗普团队沟通，以令美方可了解中方的底线及要求。有美媒消息显示，特朗普女儿伊万卡访问中国驻美大使馆前，中国驻美大使崔天凯已和伊万卡丈夫、新任白宫高级顾问贾里德·库什纳私下进行幕后会晤。由此可见，中国除了摆明态度外，一方面也寻求与最贴近特朗普的人建立沟通渠道。此次北京与特朗普的周旋过程，可看到老练的外交手腕。</p><p>　　来源：参考消息</p><p></p><p>责任编辑：刘德宾 SN222</p><p><br></p>', '2017-02-12 19:17:21', '2017-02-12 19:17:21', '5', '港媒称特朗普初步确立对华政策：中美维持斗而不破');
INSERT INTO `t_article` VALUES ('10', '<p>法制晚报快讯（记者张恩杰）今天上午，王琪的侄子王英军称老人从印度回国后有些水土不服，血压不稳定。老人的儿子、孙女由于出生在印度，吃不惯中餐，他们正在想办法联系制作印度餐的地方。由于生活习惯不同，老人的儿孙们上厕所都成问题。</p><p>　　王琪下榻的酒店工作人员则向法晚记者透露，酒店为老人准备了乾县豆腐脑、锅盔、臊子肉、小米（大米）稀饭等早餐。“我们都是按照乾县的风味做得，没有特殊加工，就是想让滞留印度54年后回归故土的老人吃到原汁原味的家乡小吃。” 该酒店厨师长向法晚记者说，他们怕王琪的儿子吃不习惯，还准备了牛奶面包。</p><p>　　王琪的侄子王战军向法晚记者表示，他们已将祖坟修葺一新，只等着王琪回乡祭祖。另外，他们还为老人打扫腾挪出了房屋供居住。</p><p>　　记者（微信公号ID：fzwb_52165216）还碰见了王琪最小的妹妹王桂玲和她的侄媳妇，她们外出给王琪买回来了棉衣，棉裤。她们说，咸阳这边春寒，老人刚从印度回来还不适应，给他添衣保暖。</p><p>　　另据了解，王琪的亲属今天还将带他去洗澡、理发。王琪的弟弟70多岁了，提前说好不哭，但刚上电梯，就控制不住自己的情绪哭了，发泄着50多年的对兄长的思念之情。</p><p></p><p>责任编辑：李伟山</p><p><br></p>', '2017-02-12 19:47:16', '2017-02-12 19:47:16', '5', '王琪回国后水土不服  血压不稳');
INSERT INTO `t_article` VALUES ('11', '<p>原标题：2017年2月17日外交部发言人耿爽主持例行记者会</p><p><img src=\"http://n.sinaimg.cn/translate/20170217/7OpN-fyarrcc7698318.jpg\" alt=\"\"></p><p>　　应外交部长王毅邀请，蒙古国对外关系部长曾德·蒙赫奥尔吉勒将于2月19日至21日对中国进行正式访问。</p><p>　　访华期间，蒙赫奥尔吉勒外长将同王毅外长举行会谈。双方将就巩固战略互信，深化务实合作，扩大人文交流等深入交换意见。他还将拜会中方领导人。中方希望通过此访能够巩固两国关系政治基础，推进双边交流与合作，推动两国关系继续向前发展。</p><p>　　问：有报道称，中国政府批准美国总统特朗普在中国建筑行业注册“特朗普”商标。有分析认为，特朗普有利用自身特殊身份从外国政府收受商业利益之嫌。你对此有何评论？能否详细介绍中国政府在有关商标注册问题上的政策？</p><p>　　答：中国商标主管机构一贯平等保护中外商标权利人的合法商标权益，并依法依规对有关商标注册申请进行审理。至于你提到的具体情况，请向主管部门了解。</p><p>　　问：“美国在台协会”台北办事处前处长16日表示，“美国在台协会”迁至新址后，美方将派海军陆战队员驻守。这是美方对台表达承诺的象征。中方对此有何回应？</p><p>　　答：我们注意到有关报道，具体情况还需进一步了解。</p><p>　　我想指出的是，中方一贯坚决反对美台进行任何形式的官方往来和军事联系，希望美方恪守一个中国政策和中美三个联合公报原则，慎重妥善处理有关涉台问题。</p><p><img src=\"http://n.sinaimg.cn/translate/20170217/gm29-fyarrcc7698325.jpg\" alt=\"\"></p><p>　　问：第一，据报道，朝鲜劳动党政治局常委崔龙海正在访华。你能否证实？第二，金正男遇害事仍受到各方关注，你对事件进展有何评论？</p><p>　　答：关于第一个问题，我不掌握你说的情况。</p><p>　　关于第二个问题，这也是大家这两天比较关注的问题，我们注意到马来西亚方面的有关表态，也注意到有关最新进展，将继续对事件保持关注。</p><p>　　问：据报道，巴基斯坦信德省一处宗教场所昨天发生一起恐怖袭击事件，造成大量人员伤亡。中方对此有何评论？</p><p>　　答：中方对发生在巴基斯坦信德省的恐怖袭击事件造成重大人员伤亡表示震惊，对袭击事件予以强烈谴责，向遇难者表示哀悼，向伤者和遇难者家属表示同情与慰问。</p><p>　　中方反对一切形式的恐怖主义，将继续坚定支持巴方打击恐怖主义，维护国家稳定和人民生命财产安全。</p><p>　　问：印中战略对话将于下周在北京举行。你能否介绍会议具体时间和主要议题？</p><p>　　答：经中印双方商定，中国外交部副部长张业遂将于2月22日与印度外秘苏杰生在北京举行新一轮中印战略对话。双方将就国际形势、双边关系和共同关心的国际地区问题等深入交换意见。</p><p>　　战略对话是中印之间重要的沟通机制。中方期待通过此次对话，增进中印政治互信，扩大战略共识，推动中印战略合作伙伴关系进一步发展。有关此次对话的具体情况，我们将适时发布消息。</p><p>　　问：据报道，中国公民张伟强因涉嫌从一家美国研究机构窃取转基因大米样本被定罪。你对此有何评论？</p><p>　　答：我没有听说过有关消息，需要作进一步了解。</p><p><img src=\"http://n.sinaimg.cn/translate/20170217/fl0n-fyarrcc7698330.jpg\" alt=\"\"></p><p>　　问：印方表示将在印中战略对话期间提及双方存在分歧的一些问题，例如印度申请加入核供应国集团（NSG）、联合国安理会1267委员会列名等问题。你对此有何回应？中方将在对话中提及哪些问题？</p><p>　　答：印度媒体一直非常关注1267委员会列名和印度申请加入NSG问题，我们也多次阐述了中方的立场。我可以再次做一下简要介绍。</p><p>　　关于1267委员会列名问题，日前有关国家又向1267委员会提出了列名申请，目前各方仍在进行讨论，尚未达成共识。我想强调的是，中方一贯本着客观、公正、专业的原则参与有关讨论。无论是对去年印方提出的列名申请，还是对今年其他国家提出的列名申请，中方采取的立场和态度都是一致的。我们坚持的列名标准只有一个，那就是要以确凿的事实为依据。如果没有确凿的事实，安理会成员之间也很难达成共识。这也从一个侧面证明，中方参与1267委员会有关讨论时采取的立场，不是从中印关系、而是从事情本身出发的。</p><p>　　关于印度申请加入NSG问题，我们多次表示，中方坚持“两步走”的思路，即NSG成员先就适用于所有非NTP缔约国的解决方案达成共识，然后再在此基础上审议个别非NPT缔约国加入的问题。除了印度之外，还有其他的非NPT缔约国也在申请加入NSG，无论对印度还是对其他申请国，中方的标准和态度也是一致的。</p><p>　　最后我想强调，无论是1267委员会列名问题，还是申请加入NSG问题，从本质上来说都是多边问题，不是双边问题，希望印方正确理解中方在上述问题上所持的态度和立场。</p><p>　　中印同为发展中大国，有广泛的共同利益。中印开展合作不仅有利于两国和两国人民，也有利于整个地区乃至整个发展中国家阵营的团结合作。中印之间存在一些分歧在所难免，但双方可以通过各种各样的交流对话，包括即将举行的新一轮战略对话，进行深入交流，尽可能缩小分歧，同时努力就进一步加强双方合作达成新的共识。</p><p>　　问：王毅外长出席G20非正式外长会期间已经分别会见了英国、德国和俄罗斯外长。他是否会和美国国务卿蒂勒森见面？王毅外长出席慕安会期间是否会见美国防部长马蒂斯？</p><p>　　答：大家都很关心王毅外长此次出席G20非正式外长会期间是否会见美国国务卿蒂勒森，我想，这恰恰证明各方都很关心中美关系发展。我们多次强调，中美关系的意义和重要性远远超出了双边范畴，一个健康稳定的中美关系符合国际社会的利益，也是国际社会的普遍期待。</p><p>　　正如你所讲，王毅外长出席G20非正式外长会期间，已经会见了部分国家外长，我们也及时发了消息，如果他会见蒂勒森国务卿，我们也会在第一时间发布消息。</p><p>　　至于王毅外长是否会见美国防部长马蒂斯，目前我没有可以发布的消息。</p><p>　　问：据报道，中国政府出台了新的《外交车辆管理办法》，将对在华外交车辆进行更严格的管理，特别是要加大对外交车辆交通违规行为的惩罚力度。你能否介绍有关情况？</p><p>　　答：随着中国对外交往不断扩大，各国驻华外交机构规模和外交车辆数量大幅增加。今年1月，外交部会同公安部、海关总署和国家税务总局等部门颁布实施了《外交车辆管理办法》，主要就是为了进一步规范对外交车辆的管理。《外交车辆管理办法》中首次提出中方将对外交车辆实行总量控制、逐辆审批的管理原则，规定实施外交车辆准驾制、强制外交车辆购买保额不低于100万元人民币的商业第三者责任保险等具体管理措施，同时明确了外交部及其他主管部门对外交车辆在购置、使用、处理各环节的管理职责和手段，要求依法、严格、规范查处外交车辆交通违法行为。中方还根据《办法》，正对各国驻华大使馆和各国际组织驻华代表机构使用的外交车辆统一换发新版号牌。</p><p>　　中国政府出台的《外交车辆管理办法》也得到了各驻华使馆和外交机构的理解和配合。他们表示，将继续尊重中方法律法规，积极配合中方实施《办法》，严格管理本馆外交车辆。</p><p></p><p>责任编辑：李鹏</p><p><br></p>', '2017-02-18 11:59:56', '2017-02-18 11:59:56', '10', '朝鲜劳动党政治局常委崔龙海正访华？中方回应');
INSERT INTO `t_article` VALUES ('12', '<p>首先进入自己的QQ邮箱，在设置中修改账户信息</p><p><img alt=\"打开邮箱设置\" src=\"http://img.blog.csdn.net/20160422235437501\"></p><p>然后来至底部&nbsp;<img alt=\"开启SMTP服务\" src=\"http://img.blog.csdn.net/20160422235817905\"></p><p>点击开启，再用手机发送对应信息到指定号码，然后点击我已发送&nbsp;<img alt=\"发送开通短信\" src=\"http://img.blog.csdn.net/20160423000054837\"></p><p>获取授权码&nbsp;<img alt=\"获取授权码\" src=\"http://img.blog.csdn.net/20160423000551658\">&nbsp;注意提示：<img alt=\"授权码\" src=\"http://img.blog.csdn.net/20160423000653065\"></p><h2>到这里，相信你已经开通了SMTP服务，这样就可以在java code发送邮件了</h2><h1>接下来的是Spring 中使用邮件服务</h1><p>首先是配置信息使用的是587端口，刚开始用465端口我纠结了好久(<a href=\"http://bbs.csdn.net/topics/391939742?page=1#post-401069188\">使用465端口的错误详情</a>)，用不了，你可以尝试，默认的25端口应该也是不适合的</p><pre><code>    &lt;!-- 邮件服务 --&gt;\r\n    &lt;bean id=\"mailSender\" class=\"org.springframework.mail.javamail.JavaMailSenderImpl\"&gt;\r\n        &lt;property name=\"host\" value=\"smtp.qq.com\"/&gt;\r\n        &lt;property name=\"port\" value=\"587\"/&gt;//或许你可以用465端口，默认的25不适合\r\n        &lt;property name=\"protocol\" value=\"smtp\"/&gt;\r\n        &lt;property name=\"username\" value=\"785427346@qq.com\"/&gt;\r\n        &lt;property name=\"password\" value=\"xxxxxxxxxxxx\"/&gt;//这里的是你通过短信后，获取的授权码\r\n        &lt;property name=\"defaultEncoding\" value=\"UTF-8\"/&gt;\r\n        &lt;property name=\"javaMailProperties\"&gt;  \r\n            &lt;props&gt;  \r\n                &lt;prop key=\"mail.smtp.auth\"&gt;true&lt;/prop&gt; \r\n                &lt;prop key=\"mail.smtp.timeout\"&gt;25000&lt;/prop&gt;  \r\n            &lt;/props&gt;  \r\n        &lt;/property&gt;  \r\n\r\n    &lt;/bean&gt;\r\n\r\n    &lt;!-- this is a template message that we can pre-load with default state --&gt;\r\n    &lt;bean id=\"templateMessage\" class=\"org.springframework.mail.SimpleMailMessage\"&gt;\r\n        &lt;property name=\"from\" value=\"785427346@qq.com\"/&gt;\r\n        &lt;property name=\"subject\" value=\"尝试发邮件\"/&gt;\r\n    &lt;/bean&gt;\r\n\r\n    &lt;bean id=\"orderManager\" class=\"cn.cherish.common.SimpleOrderManager\"&gt;\r\n        &lt;property name=\"mailSender\" ref=\"mailSender\"/&gt;\r\n        &lt;property name=\"templateMessage\" ref=\"templateMessage\"/&gt;\r\n    &lt;/bean&gt;</code></pre><p>用maven引入的jar包</p><pre><code>    &lt;!-- 邮件 --&gt;\r\n    &lt;dependency&gt;\r\n        &lt;groupId&gt;org.springframework&lt;/groupId&gt;\r\n        &lt;artifactId&gt;spring-context-support&lt;/artifactId&gt;\r\n        &lt;version&gt;${spring.version}&lt;/version&gt;\r\n    &lt;/dependency&gt;\r\n\r\n    &lt;dependency&gt;\r\n        &lt;groupId&gt;javax.mail&lt;/groupId&gt;\r\n        &lt;artifactId&gt;mail&lt;/artifactId&gt;\r\n        &lt;version&gt;1.4.7&lt;/version&gt;\r\n    &lt;/dependency&gt;</code></pre><p>下面只是一个工具类作简单例子，请勿见怪</p><pre><code>package cn.cherish.common;\r\n\r\nimport java.io.BufferedReader;\r\nimport java.io.File;\r\nimport java.io.InputStream;\r\nimport java.io.InputStreamReader;\r\nimport java.net.URL;\r\nimport java.net.URLConnection;\r\nimport java.util.Properties;\r\n\r\nimport javax.mail.MessagingException;\r\nimport javax.mail.internet.MimeMessage;\r\n\r\nimport org.springframework.core.io.FileSystemResource;\r\nimport org.springframework.mail.MailException;\r\nimport org.springframework.mail.SimpleMailMessage;\r\nimport org.springframework.mail.javamail.JavaMailSenderImpl;\r\nimport org.springframework.mail.javamail.MimeMessageHelper;\r\n\r\n/**\r\n * 项目名称：springmvc_hibernate\r\n * 类名称：MailUtil\r\n * 类描述：\r\n * 创建人：Cherish\r\n * 联系方式：785427346@qq.com\r\n * 创建时间：2016年4月22日 下午3:51:48\r\n * @version 1.0\r\n */\r\npublic class MailUtil {\r\n\r\n    private static final String HOST = \"smtp.qq.com\";\r\n    private static final String SMTP = \"smtp\";\r\n    private static final String USERNAME = \"785427346@qq.com\";\r\n    private static final String PASSWORD = \"xxxxxxxxxx\";\r\n    private static final int PORT = 587;//587/465\r\n    private static final String DEFAULTENCODING = \"UTF-8\";\r\n\r\n    private static JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();\r\n\r\n    private static Properties prop = new Properties();\r\n\r\n    static{\r\n        // 设定mail server\r\n        senderImpl.setHost(HOST);\r\n        senderImpl.setProtocol(SMTP);\r\n        senderImpl.setUsername(USERNAME);\r\n        senderImpl.setPassword(PASSWORD);\r\n        senderImpl.setPort(PORT);\r\n        senderImpl.setDefaultEncoding(DEFAULTENCODING);\r\n\r\n        // 设定properties\r\n        prop.put(\"mail.smtp.auth\", \"true\");\r\n        prop.put(\"mail.smtp.timeout\", \"25000\");\r\n        //设置调试模式可以在控制台查看发送过程\r\n        prop.put(\"mail.debug\", \"true\");\r\n\r\n        senderImpl.setJavaMailProperties(prop);\r\n    }\r\n\r\n    public static void main(String args[]) {\r\n        // 设置收件人，寄件人 用数组发送多个邮件\r\n//      String[] array = new String[] {\"88888@qq.com\",\"666666@qq.com\",\"999999999@qq.com\",USERNAME};\r\n        String[] array = new String[] {USERNAME};\r\n        String subject = \"Cherish内嵌图片、音乐的邮件\";\r\n\r\n//      StringBuffer sb = new StringBuffer();\r\n//      try {\r\n//          URL url = new URL(\"http://www.imooc.com/\");//http://android-studio.org/\r\n//          \r\n//          URLConnection conn = url.openConnection();\r\n//          InputStream is = conn.getInputStream();\r\n//          \r\n//          BufferedReader reader = new BufferedReader(new InputStreamReader(is));\r\n//          \r\n//          String string = null;\r\n//          while ((string = reader.readLine()) != null) {\r\n//              sb.append(string);\r\n//          }\r\n//          \r\n//          //System.out.println(sb.toString());\r\n//          \r\n//      } catch (Exception e) {\r\n//          e.printStackTrace();\r\n//      }\r\n//      \r\n//      boolean result = htmlMail(array, subject, sb.toString());\r\n\r\n        String filePath = \"E:/javaxmail.png\";\r\n        String html = \"&lt;html&gt;&lt;head&gt;\"+\r\n                    \"&lt;/head&gt;&lt;body&gt;\"+\r\n                    \"&lt;audio src=\'http://m10.music.126.net/20160422225433/25b43b999bcdaf3425b9194514340596/ymusic/8c94/b9af/69e3/7ebe35b8e00154120822550b21b0c9c5.mp3\' autoplay=\'autoplay\' controls=\'controls\' loop=\'-1\'&gt;爱你&lt;/audio&gt;\"+\r\n                    \"&lt;h1&gt;Hello,Nice to meet you!&lt;/h1&gt;\"+\r\n                    \"&lt;span style=\'color:red;font-size:36px;\'&gt;并摸了一把你的小奶&lt;/span&gt;\"+\r\n                    \"&lt;img src=\'cid:javaxmail.png\'&gt;\"+\r\n                    \"&lt;/body&gt;&lt;/html&gt;\";\r\n        boolean result = inlineFileMail(array, subject, html, filePath);\r\n\r\n        if (result) {\r\n            System.out.println(\"发送邮件成功。。。。\");\r\n        }\r\n\r\n\r\n    }\r\n\r\n    /**\r\n     * 发送简单邮件\r\n     * @param to 收件人邮箱\r\n     * @param subject 主题\r\n     * @param content 内容\r\n     * @return\r\n     */\r\n    public static boolean singleMail(String to, String subject, String content){\r\n        String[] array = new String[] {to};\r\n        return singleMail(array, subject, content);\r\n    }\r\n\r\n\r\n    /**\r\n     * 发送简单文本邮件\r\n     * @param to 收件人邮箱数组\r\n     * @param subject 主题\r\n     * @param content 内容\r\n     * @return\r\n     */\r\n    public static boolean singleMail(String[] to, String subject, String content){\r\n        boolean result = true;\r\n\r\n        SimpleMailMessage mailMessage = new SimpleMailMessage();\r\n        // 设置收件人，寄件人 用数组发送多个邮件\r\n        mailMessage.setTo(to);\r\n        mailMessage.setFrom(USERNAME);\r\n        mailMessage.setSubject(subject);\r\n        mailMessage.setText(content);\r\n        // 发送邮件\r\n        try {\r\n            senderImpl.send(mailMessage);\r\n        } catch (MailException e) {\r\n            e.printStackTrace();\r\n            result = false;\r\n        }\r\n        return result;\r\n    }\r\n\r\n\r\n    /**\r\n     * 发送html邮件\r\n     * @param to 收件人\r\n     * @param subject 主题\r\n     * @param html html代码\r\n     * @return\r\n     */\r\n    public static boolean htmlMail(String[] to, String subject, String html){\r\n        boolean result = true;\r\n\r\n        MimeMessage mailMessage = senderImpl.createMimeMessage();  \r\n        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);  \r\n\r\n        try {\r\n            // 设置收件人，寄件人 用数组发送多个邮件\r\n            messageHelper.setTo(to);\r\n            messageHelper.setFrom(USERNAME);\r\n            messageHelper.setSubject(subject);\r\n            // true 表示启动HTML格式的邮件  \r\n            messageHelper.setText(html, true);  \r\n\r\n            // 发送邮件\r\n            senderImpl.send(mailMessage);\r\n        } catch (MessagingException e) {\r\n            result = false;\r\n            e.printStackTrace();\r\n        }\r\n        return result;\r\n    }\r\n\r\n\r\n    /**\r\n     * 发送内嵌图片的邮件   （cid:资源名）\r\n     * @param to 收件人邮箱\r\n     * @param subject 主题\r\n     * @param html html代码\r\n     * @param imgPath 图片路径\r\n     * @return\r\n     */\r\n    public static boolean inlineFileMail(String[] to, String subject, String html, String filePath){\r\n        boolean result = true;\r\n\r\n        MimeMessage mailMessage = senderImpl.createMimeMessage();  \r\n        try {\r\n            //设置true开启嵌入图片的功能\r\n            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true);  \r\n            // 设置收件人，寄件人 用数组发送多个邮件\r\n            messageHelper.setTo(to);\r\n            messageHelper.setFrom(USERNAME);\r\n            messageHelper.setSubject(subject);\r\n            // true 表示启动HTML格式的邮件  \r\n            messageHelper.setText(html, true);  \r\n\r\n            FileSystemResource file = new FileSystemResource(new File(filePath));  \r\n            messageHelper.addInline(file.getFilename(), file);  \r\n\r\n            // 发送邮件\r\n            senderImpl.send(mailMessage);\r\n        } catch (MessagingException e) {\r\n            result = false;\r\n            e.printStackTrace();\r\n        }\r\n        return result;\r\n    }\r\n\r\n\r\n    /**\r\n     * 发送带附件的邮件\r\n     * @param to\r\n     * @param subject\r\n     * @param html\r\n     * @param filePath\r\n     * @return\r\n     */\r\n    public static boolean attachedFileMail(String[] to, String subject, String html, String filePath){\r\n        boolean result = true;\r\n\r\n        MimeMessage mailMessage = senderImpl.createMimeMessage();  \r\n\r\n        try {\r\n            // multipart模式 为true时发送附件 可以设置html格式\r\n            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,\"utf-8\");  \r\n            // 设置收件人，寄件人 用数组发送多个邮件\r\n            messageHelper.setTo(to);\r\n            messageHelper.setFrom(USERNAME);\r\n            messageHelper.setSubject(subject);\r\n            // true 表示启动HTML格式的邮件  \r\n            messageHelper.setText(html, true);  \r\n\r\n            FileSystemResource file = new FileSystemResource(new File(filePath));  \r\n            // 这里的方法调用和插入图片是不同的。  \r\n            messageHelper.addAttachment(file.getFilename(), file);\r\n\r\n            // 发送邮件\r\n            senderImpl.send(mailMessage);\r\n        } catch (MessagingException e) {\r\n            result = false;\r\n            e.printStackTrace();\r\n        }\r\n        return result;\r\n    }\r\n\r\n\r\n\r\n}</code></pre><h2>温馨提示：</h2><pre><code>&lt;img src=\'cid:javaxmail.png\'&gt;\r\n这是内嵌图片的方式 javaxmail.png 要和 messageHelper.addInline(file.getFilename(), file); 这里的 file.getFilename() 相一致就可以显示</code></pre><p>现在只差一步了，那就是Ctrl + F11，有不当之处敬请提出，共同进步&nbsp;<img alt=\"这里写图片描述\" src=\"http://img.blog.csdn.net/20160423085829013\"></p><p>**</p><h2>使用javax.mail发邮件代码</h2><p>**</p><pre><code>package cn.cherish.utils;\r\n\r\nimport java.io.File;\r\nimport java.io.FileNotFoundException;\r\nimport java.io.IOException;\r\nimport java.util.Date;\r\nimport java.util.Properties;\r\n\r\nimport javax.activation.DataHandler;\r\nimport javax.activation.DataSource;\r\nimport javax.activation.FileDataSource;\r\nimport javax.mail.Authenticator;\r\nimport javax.mail.BodyPart;\r\nimport javax.mail.Message;\r\nimport javax.mail.MessagingException;\r\nimport javax.mail.Multipart;\r\nimport javax.mail.PasswordAuthentication;\r\nimport javax.mail.Session;\r\nimport javax.mail.Transport;\r\nimport javax.mail.internet.InternetAddress;\r\nimport javax.mail.internet.MimeBodyPart;\r\nimport javax.mail.internet.MimeMessage;\r\nimport javax.mail.internet.MimeMessage.RecipientType;\r\nimport javax.mail.internet.MimeMultipart;\r\nimport javax.mail.internet.MimeUtility;\r\n\r\n/**\r\n * 项目名称：springmvc_hibernate \r\n * 类名称：EmailUtil \r\n * 类描述：发送邮件工具类 \r\n * 创建人：Cherish\r\n * 联系方式：785427346@qq.com \r\n * 创建时间：2016年4月23日 上午9:48:21\r\n * @version 1.0\r\n */\r\npublic class EmailUtil {\r\n\r\n    // properties配置文件地址\r\n    //private static final String PROPERTIES_PATH = \"standard_data.properties\";\r\n\r\n    private static Session session;\r\n    private static Properties props = new Properties();\r\n    private static final String HOST = \"smtp.qq.com\";\r\n    private static int PORT = 587;\r\n    private static final String isAUTH = \"true\";\r\n    private static final String FROM = \"785427346@qq.com\";\r\n\r\n    private static final String USERNAME = \"785427346@qq.com\";\r\n    private static final String PASSWORD = \"xxxxxxxxxxxxxxxx\";\r\n\r\n    private static final String TIMEOUT = \"25000\";\r\n    private static final String DEBUG = \"true\";\r\n\r\n    // 初始化session\r\n    static {\r\n        props.put(\"mail.smtp.host\", HOST);\r\n        props.put(\"mail.smtp.port\", PORT);\r\n        props.put(\"mail.smtp.auth\", isAUTH);\r\n        props.put(\"fromer\", FROM);\r\n        props.put(\"username\", USERNAME);\r\n        props.put(\"password\", PASSWORD);\r\n        props.put(\"mail.smtp.timeout\", TIMEOUT);\r\n        props.put(\"mail.debug\", DEBUG);\r\n\r\n        session = Session.getInstance(props, new Authenticator() {\r\n            @Override\r\n            protected PasswordAuthentication getPasswordAuthentication() {\r\n                return new PasswordAuthentication(USERNAME, PASSWORD);\r\n            }\r\n        });\r\n    }\r\n\r\n    public static void main(String[] args) {\r\n        try {\r\n            String html = \"&lt;html&gt;&lt;head&gt;\"+\r\n                    \"&lt;/head&gt;&lt;body&gt;\"+\r\n                    \"&lt;audio src=\'http://219.128.78.22/m10.music.126.net/20160423105749/3cee5688a7dc87d28a265fd992ecb0a2/ymusic/8c94/b9af/69e3/7ebe35b8e00154120822550b21b0c9c5.mp3?wshc_tag=1&amp;wsts_tag=571aded1&amp;wsid_tag=b73f773e&amp;wsiphost=ipdbm\' autoplay=\'autoplay\' controls=\'controls\' loop=\'-1\'&gt;爱你&lt;/audio&gt;\"+\r\n                    \"&lt;video controls=\'controls\'&gt;\"+\r\n                    \"&lt;source src=\'http://v2.mukewang.com/45ad4643-87d7-444b-a3b9-fbf32de63811/H.mp4?auth_key=1461379796-0-0-e86cefa71cef963875fd68f8a419dd8a\' type=\'video/mp4\' /&gt;\"+\r\n                    \"Your browser does not support the video tag.\"+\r\n                    \"&lt;/video&gt;\"+\r\n                    \"&lt;h1&gt;Hello,nice to fuck you!&lt;/h1&gt;\"+\r\n                    \"&lt;span style=\'color:red;font-size:36px;\'&gt;并抓了一把你的小鸡鸡&lt;/span&gt;\"+\r\n                    \"&lt;/body&gt;&lt;/html&gt;\";\r\n\r\n            //sendEmail(\"785427346@qq.com\", \"yeah\", html, true);\r\n\r\n\r\n            sendFileEmail(\"785427346@qq.com\", \"yeah\", html, new File(\"E:/xiaoming.zip\"));\r\n        } catch (Exception e) {\r\n            e.printStackTrace();\r\n        }\r\n    }\r\n\r\n\r\n    /**\r\n     * \r\n     * @Title sendEmail\r\n     * @Description 通过isHtml判断发送的邮件的内容\r\n     * @param to 邮件接收者\r\n     * @param content 邮件内容\r\n     * @param isHtml 是否发送html\r\n     * @throws MessagingException\r\n     * @throws IOException\r\n     * @throws FileNotFoundException\r\n     * @throws EmailException\r\n     */\r\n    public static void sendEmail(String to, String title, String content, boolean isHtml)\r\n            throws FileNotFoundException, IOException, MessagingException {\r\n        String fromer = props.getProperty(\"fromer\");\r\n        if (isHtml) {\r\n            sendHtmlEmail(fromer, to, title, content);\r\n        } else {\r\n            sendTextEmail(fromer, to, title, content);\r\n        }\r\n    }\r\n\r\n    // 发送纯文字邮件\r\n    public static void sendTextEmail(String from, String to, String subject, String content)\r\n            throws FileNotFoundException, IOException, MessagingException {\r\n\r\n        Message message = new MimeMessage(session);\r\n        message.setFrom(new InternetAddress(from));\r\n        message.setRecipient(RecipientType.TO, new InternetAddress(to));\r\n        message.setSubject(subject);\r\n        message.setText(content);\r\n        message.setSentDate(new Date());\r\n        Transport.send(message);\r\n    }\r\n\r\n    // 发送有HTML格式邮件\r\n    public static void sendHtmlEmail(String from, String to, String subject, String htmlConent)\r\n            throws FileNotFoundException, IOException, MessagingException {\r\n\r\n        Message message = new MimeMessage(session);\r\n        message.setFrom(new InternetAddress(from));\r\n        message.setRecipient(RecipientType.TO, new InternetAddress(to));\r\n        message.setSubject(subject);\r\n        message.setSentDate(new Date());\r\n\r\n        Multipart multi = new MimeMultipart();\r\n        BodyPart html = new MimeBodyPart();\r\n        html.setContent(htmlConent, \"text/html; charset=utf-8\");\r\n        multi.addBodyPart(html);\r\n        message.setContent(multi);\r\n        Transport.send(message);\r\n    }\r\n\r\n    // 发送带附件的邮件\r\n    public static void sendFileEmail(String to, String subject, String htmlConent, File attachment)\r\n            throws FileNotFoundException, IOException, MessagingException {\r\n\r\n        Message message = new MimeMessage(session);\r\n        String fromer = props.getProperty(\"fromer\");\r\n        message.setFrom(new InternetAddress(fromer));\r\n        message.setRecipient(RecipientType.TO, new InternetAddress(to));\r\n        message.setSubject(subject);\r\n        message.setSentDate(new Date());\r\n        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件\r\n        Multipart multipart = new MimeMultipart();\r\n        // 添加邮件正文\r\n        BodyPart contentPart = new MimeBodyPart();\r\n        contentPart.setContent(htmlConent, \"text/html;charset=UTF-8\");\r\n        multipart.addBodyPart(contentPart);\r\n        // 添加附件的内容\r\n        if (attachment != null) {\r\n            BodyPart attachmentBodyPart = new MimeBodyPart();\r\n            DataSource source = new FileDataSource(attachment);\r\n            attachmentBodyPart.setDataHandler(new DataHandler(source));\r\n\r\n            // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定\r\n            // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码\r\n            // sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();\r\n            // messageBodyPart.setFileName(\"=?GBK?B?\" +\r\n            // enc.encode(attachment.getName().getBytes()) + \"?=\");\r\n            // MimeUtility.encodeWord可以避免文件名乱码\r\n            attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));\r\n            multipart.addBodyPart(attachmentBodyPart);\r\n        }\r\n\r\n        message.setContent(multipart);\r\n        Transport.send(message);\r\n    }\r\n\r\n\r\n}\r\n</code></pre><p><a href=\"http://blog.csdn.net/caimengyuan/article/details/51224269#\"></a><a href=\"http://blog.csdn.net/caimengyuan/article/details/51224269#\"></a><a href=\"http://blog.csdn.net/caimengyuan/article/details/51224269#\"></a><a href=\"http://blog.csdn.net/caimengyuan/article/details/51224269#\"></a><a href=\"http://blog.csdn.net/caimengyuan/article/details/51224269#\"></a><a href=\"http://blog.csdn.net/caimengyuan/article/details/51224269#\"></a></p><dl><br><!--EndFragment--></dl><p><br></p>', '2017-02-18 16:27:05', '2017-02-18 21:35:55', '1599', 'java spring 开启SMTP服务发送QQ邮件');

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_active` int(11) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` datetime DEFAULT NULL,
  `nickname` varchar(32) NOT NULL,
  `password` varchar(16) NOT NULL,
  `telephone` varchar(11) NOT NULL,
  `weixinuser_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hhsuwu3qpbo96h3o6osun9v86` (`telephone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer
-- ----------------------------

-- ----------------------------
-- Table structure for t_history
-- ----------------------------
DROP TABLE IF EXISTS `t_history`;
CREATE TABLE `t_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` datetime NOT NULL,
  `description` varchar(1024) NOT NULL DEFAULT '',
  `name` varchar(32) NOT NULL,
  `type` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_history
-- ----------------------------

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(1024) NOT NULL DEFAULT '',
  `permit` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', '编辑用户', 'user:update');
INSERT INTO `t_permission` VALUES ('2', '删除用户', 'user:delete');
INSERT INTO `t_permission` VALUES ('3', '新增用户', 'user:add');
INSERT INTO `t_permission` VALUES ('4', '查看用户', 'user:show');
INSERT INTO `t_permission` VALUES ('5', '新增企业资讯', 'info:add');
INSERT INTO `t_permission` VALUES ('6', '删除企业资讯', 'info:delete');
INSERT INTO `t_permission` VALUES ('7', '更新企业资讯', 'info:update');
INSERT INTO `t_permission` VALUES ('8', '查看企业资讯', 'info:show');
INSERT INTO `t_permission` VALUES ('9', '查看客户信息', 'customer:show');
INSERT INTO `t_permission` VALUES ('10', '客户信息修改', 'customer:update');
INSERT INTO `t_permission` VALUES ('11', '删除客户/冻结', 'customer:delete');
INSERT INTO `t_permission` VALUES ('12', '新增客户，帮客户注册', 'customer:add');

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_active` int(11) NOT NULL,
  `created_time` datetime NOT NULL,
  `description` varchar(1024) NOT NULL DEFAULT '',
  `inventory` int(11) NOT NULL,
  `modified_time` datetime DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  `price` decimal(4,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(1024) NOT NULL DEFAULT '',
  `name` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员', 'admin');
INSERT INTO `t_role` VALUES ('2', '超级管理员', 'super');
INSERT INTO `t_role` VALUES ('4', '接待员/前台人员/收银员', 'receptionist');
INSERT INTO `t_role` VALUES ('5', '编写人员/资讯编辑/资料收录', 'editor');
INSERT INTO `t_role` VALUES ('6', '大师傅', 'master');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  KEY `FKjobmrl6dorhlfite4u34hciik` (`permission_id`),
  KEY `FK90j038mnbnthgkc17mqnoilu9` (`role_id`),
  CONSTRAINT `FK90j038mnbnthgkc17mqnoilu9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FKjobmrl6dorhlfite4u34hciik` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('5', '5');
INSERT INTO `t_role_permission` VALUES ('5', '6');
INSERT INTO `t_role_permission` VALUES ('5', '7');
INSERT INTO `t_role_permission` VALUES ('5', '8');
INSERT INTO `t_role_permission` VALUES ('1', '1');
INSERT INTO `t_role_permission` VALUES ('1', '2');
INSERT INTO `t_role_permission` VALUES ('1', '3');
INSERT INTO `t_role_permission` VALUES ('1', '4');
INSERT INTO `t_role_permission` VALUES ('1', '5');
INSERT INTO `t_role_permission` VALUES ('1', '6');
INSERT INTO `t_role_permission` VALUES ('1', '7');
INSERT INTO `t_role_permission` VALUES ('1', '8');
INSERT INTO `t_role_permission` VALUES ('1', '9');
INSERT INTO `t_role_permission` VALUES ('1', '10');
INSERT INTO `t_role_permission` VALUES ('1', '11');
INSERT INTO `t_role_permission` VALUES ('1', '12');
INSERT INTO `t_role_permission` VALUES ('4', '9');
INSERT INTO `t_role_permission` VALUES ('2', '1');
INSERT INTO `t_role_permission` VALUES ('2', '2');
INSERT INTO `t_role_permission` VALUES ('2', '3');
INSERT INTO `t_role_permission` VALUES ('2', '4');
INSERT INTO `t_role_permission` VALUES ('2', '5');
INSERT INTO `t_role_permission` VALUES ('2', '6');
INSERT INTO `t_role_permission` VALUES ('2', '7');
INSERT INTO `t_role_permission` VALUES ('2', '8');
INSERT INTO `t_role_permission` VALUES ('2', '9');
INSERT INTO `t_role_permission` VALUES ('2', '10');
INSERT INTO `t_role_permission` VALUES ('2', '11');
INSERT INTO `t_role_permission` VALUES ('2', '12');

-- ----------------------------
-- Table structure for t_store
-- ----------------------------
DROP TABLE IF EXISTS `t_store`;
CREATE TABLE `t_store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(64) NOT NULL,
  `area` varchar(16) NOT NULL,
  `city` varchar(16) NOT NULL,
  `description` varchar(1024) NOT NULL DEFAULT '',
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `province` varchar(16) NOT NULL,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_store
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_active` tinyint(6) NOT NULL,
  `created_time` datetime NOT NULL,
  `description` varchar(1024) NOT NULL DEFAULT '',
  `modified_time` datetime NOT NULL,
  `hiredate` datetime NOT NULL,
  `nickname` varchar(16) NOT NULL,
  `password` varchar(40) NOT NULL,
  `telephone` varchar(16) NOT NULL,
  `username` varchar(16) NOT NULL,
  `position` varchar(16) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '1', '2017-01-31 23:13:55', '就是我', '2017-02-05 15:15:55', '2017-02-03 23:00:44', 'Cherish', '342401bf3afbe8b5f7c742f99b4759bf4a21a933', '18826137274', 'cherish', '超级大哥', '0');
INSERT INTO `t_user` VALUES ('2', '1', '2017-01-31 23:26:50', '好像还是有点小问题', '2017-02-18 18:43:52', '2017-02-03 00:00:00', 'admin1', '342401bf3afbe8b5f7c742f99b4759bf4a21a933', '18826137274', 'admin1', '煮饭做吃', '0');
INSERT INTO `t_user` VALUES ('3', '1', '2017-02-06 10:49:35', '没有备注', '2017-02-18 18:23:19', '2017-02-06 00:00:00', '蔡大哥', 'd70c0b393abe4f664f1e461b777cc1df1ef8ce15', '18826137274', 'admin2', '洗碗清洁', '0');
INSERT INTO `t_user` VALUES ('4', '0', '2017-02-06 11:46:46', '再次冻结', '2017-02-18 18:19:44', '2017-02-06 00:00:00', 'admin3', 'ea903fb94fce27574d59598a4301989d05297471', '18826137274', 'admin3', '斟茶递水', '0');
INSERT INTO `t_user` VALUES ('5', '1', '2017-02-06 11:52:12', '新建冻结不行么', '2017-02-18 16:28:04', '2017-01-01 00:00:00', 'admin4', 'e76f1293fec32b13ee1b43951a5179bf11dbae1d', '18826137274', 'admin4', '管理员', '0');
INSERT INTO `t_user` VALUES ('6', '0', '2017-02-06 14:10:27', 'admin5', '2017-02-24 15:43:17', '2017-02-06 00:00:00', 'admin5', 'df1da040fa651b871219bb22af9c060b8e71ea6a', '18826137274', 'admin5', 'admin5', '0');
INSERT INTO `t_user` VALUES ('7', '1', '2017-02-17 00:45:56', 'admin6', '2017-02-17 00:45:56', '2017-02-17 00:00:00', 'admin6', '029939f71abfa3cde9ca2807eb8916fa3d75d656', '18826137274', 'admin6', 'gg', '0');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKa9c8iiy6ut0gnx491fqx4pxam` (`role_id`),
  KEY `FKq5un6x7ecoef5w1n39cop66kl` (`user_id`),
  CONSTRAINT `FKa9c8iiy6ut0gnx491fqx4pxam` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FKq5un6x7ecoef5w1n39cop66kl` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('2', '1');
INSERT INTO `t_user_role` VALUES ('2', '2');
INSERT INTO `t_user_role` VALUES ('3', '1');
INSERT INTO `t_user_role` VALUES ('6', '1');
INSERT INTO `t_user_role` VALUES ('1', '1');
INSERT INTO `t_user_role` VALUES ('1', '2');

-- ----------------------------
-- Table structure for t_weixinuser
-- ----------------------------
DROP TABLE IF EXISTS `t_weixinuser`;
CREATE TABLE `t_weixinuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(10) DEFAULT NULL,
  `headimgurl` varchar(255) DEFAULT NULL,
  `nickname` varchar(32) DEFAULT NULL,
  `openid` varchar(32) NOT NULL,
  `sex` smallint(6) DEFAULT NULL,
  `subscribe_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_weixinuser
-- ----------------------------
