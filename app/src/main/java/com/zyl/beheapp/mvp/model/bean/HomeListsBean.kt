package com.zyl.beheapp.mvp.model.bean

data class HomeListsBean(
        val code: Int, //200
        val data: Data,
        val message: String //OK
)

data class Data(
        val items: List<Item>,
        val paging: Paging,
        val banners: List<Banner>
)

data class Paging(
        val next_url: String //http://api.bohejiaju.com/v2/channels/4/items?generation=4&gender=1&limit=20&offset=20
)

data class Item(
        val content_url: String, //http://bohejiaju.liwushuo.com/posts/4545/content
        val cover_image_url: String, //http://7fvaoh.com3.z0.glb.qiniucdn.com/image/151217/4a9nyyog4.jpg-w720
        val created_at: Int, //1451174400
        val editor_id: Any, //null
        val id: Int, //4545
        val labels: List<Any>,
        val liked: Boolean, //false
        val likes_count: Int, //7252
        val published_at: Int, //1451174400
        val share_msg: String, //最近出了超多美食节目，对于真正的吃货行家来说，最关心的显然不是那些明星们，而是他们使用的厨房工具，毕竟&ldquo;工欲善其事必先利其器&rdquo;嘛！下面我为各位大厨推荐几款厨房好帮手，有了这些神器，相信你可以做出超级好吃的美味哦~人生在世，吃点好的很有必要！这些厨房美食小利器，相信也能帮到你！
        val short_title: String, //美食炊具
        val status: Int, //0
        val template: String,
        val title: String, //美食神器强助攻，超级美味也能亲手烹饪
        val type: String, //post
        val updated_at: Long, //1450330467
        val url: String, //http://bohejiaju.liwushuo.com/posts/4545

        val data: Datas
)

data class Banner(
        val channel: String, //all
        val id: Int, //21
        val image_url: String, //http://7fvaoh.com3.z0.glb.qiniucdn.com/image/151104/69t9ogkgl.jpg-w720
        val order: Int, //3
        val status: Int, //0
        val target: Target,
        val target_id: Int, //19
        val target_url: String,
        val type: String //collection
)

data class Target(
        val banner_image_url: String, //http://7fvaoh.com3.z0.glb.qiniucdn.com/image/151104/s8a68uk0k.jpg-w300
        val cover_image_url: String, //http://7fvaoh.com3.z0.glb.qiniucdn.com/image/151104/84zzlpbwy.jpg-w720
        val created_at: Int, //1446627781
        val id: Int, //19
        val posts_count: Int, //3
        val status: Int, //0
        val subtitle: Any, //null
        val title: String, //没人能够拒绝的zakka风家居
        val updated_at: Int //1446627781

)

data class Datas(
        val brand_id: Any, //null
        val brand_order: Int, //0
        val category_id: Any, //null
        val cover_image_url: String, //http://7fvaoh.com3.z0.glb.qiniucdn.com/image/150831/d1fbf6m6s_w.jpg-w720
        val created_at: Int, //1441001347
        val description: String, //颜色超心水，湖水蓝搭配白色的墙壁，绝配哦~阳台灯光一般都会很暗，一串这样的彩灯，既能起到装饰作用，也很实用。
        val editor_id: Int, //1024
        val favorites_count: Int, //5001
        val id: Int, //1144
        val image_urls: List<String>,
        val is_favorite: Boolean, //false
        val name: String, //湖水绿棉线圆球彩灯串
        val post_ids: List<Any>,
        val price: String, //35.14
        val purchase_id: String, //39121834560
        val purchase_status: Int, //1
        val purchase_type: Int, //1
        val purchase_url: String, //http://s.click.taobao.com/t?e=m%3D2%26s%3D%2FHrjRRF4G6kcQipKwQzePOeEDrYVVa64Qih%2F7PxfOKS5VBFTL4hn2a2jaQ927FGU%2FpU2SWJU0cJtabAtTg06px%2FVXctKptzdeP1OMBEVuKWDXUm1ysSg%2B79yb5fgD6Zv1aNkTR84XM04KSD%2FjpIbDnEqY%2Bakgpmw
        val subcategory_id: Any, //null
        val updated_at: Int, //1441001347
        val url: String //http://bohejiaju.liwushuo.com/items/1144
)