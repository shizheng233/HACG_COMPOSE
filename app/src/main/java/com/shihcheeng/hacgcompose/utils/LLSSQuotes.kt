/*
无意中发现的。
 */
package com.shihcheeng.hacgcompose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class LLSSQuotes private constructor(private val list: List<String>) {

    fun random(): String {
        return list.random()
    }

    @Composable
    fun rememberRandom(): String = remember {
        list.random()
    }

    companion object {

        /**
         * 创建琉璃神社下面的动漫语录集合。
         */
        @Composable
        fun create(): LLSSQuotes = remember {
            val quotes = Array(31) { "" }
            quotes[0] = "得到了不该得到的，就会失去不该失去的。"
            quotes[1] =
                "隐约雷鸣 阴霾天空 但盼风雨来 能留你在此 隐约雷鸣 阴霾天空 即使天无雨 我亦留此地《言叶之庭》"
            quotes[2] = "真正重要的东西，就算失去自己珍贵的生命，也要用双手保护到底。《秦时明月》"
            quotes[3] = "这世上所有的不公平都是因为当事人能力的不足。《东京食尸鬼》"
            quotes[4] =
                "要想成为强者，就不要回避心里的恐惧，恐惧并不是弱点。强者，是要让你的敌人比你更恐惧 。《秦时明月》"
            quotes[5] =
                "若今生执罔虚幻，已成落花。便许你来世雪扫眉发，执手天涯。 你可愿青丝绾正，笑靥如花，借我一世年华。《秦时明月》"
            quotes[6] = "失败的人只有一种。就是在抵达成功之前放弃的人"
            quotes[7] = "你什么也不肯放弃，又得到了什么？"
            quotes[8] = "勇敢，不是靠别人为他担心而证明的，强者，要能够使亲人和朋友感觉到安全和放心。"
            quotes[9] = "弱者，总喜欢用理由来解释结果。"
            quotes[10] = "喜欢的人喜欢别人不是很正常吗。《四月是你的谎言》"
            quotes[11] =
                "或许前路永夜，即便如此我也要前进，因为星光即使微弱也会为我照亮前路《四月是你的谎言》"
            quotes[12] =
                "人活着是没有意义的,但只有活下去,才能发现快乐的事情。就如你看到那花,就如我看到你。《火影忍者》"
            quotes[13] = "不相信自己的人 连努力的价值都没有。《火影忍者》"
            quotes[14] =
                "我们总是在注意错过太多，却不注意自己拥有多少。《我们仍未知道那天所看见的花的名字》"
            quotes[15] = "我的愿望是，将一位少女拥入怀中，而拯救世界，只不过是顺带罢了。《罪恶王冠》"
            quotes[16] = "只要有你在，我就无所不能。——三笠《进击的巨人》"
            quotes[17] = "不要想一味的改变现在，这只会让过去变得面目全非罢了。《命运石之门》"
            quotes[18] = "无意义的理想,迟早会在现实面前崩溃。《fate zero》"
            quotes[19] = "能哭的地方，只有厕所，和爸爸的怀里。《Clannad》》"
            quotes[20] = "教练，我想打篮球。《灌篮高手》"
            quotes[21] = "我只是喜欢看人们恐惧的表情。对，就是你这个表情。——沙鲁《七龙珠》"
            quotes[22] = "今天的风儿有点喧嚣《男子高校生的日常》"
            quotes[23] = "正因为生来什么都没有，因此我们能拥有一切。《游戏人生》"
            quotes[24] = "与其坐等汉化，不如好好学外语。《琉璃神社》"
            quotes[25] = "只要把垃圾都藏在黑暗之中，世界看起来自然就歌舞升平了。《海贼王》"
            quotes[26] = "弱者，连死的方式都无从选择。《海贼王》"
            quotes[27] = "来者勿拒，去者不追。"
            quotes[28] = "自由不是无法无天而是按照自己的规则活下去。《银魂》"
            quotes[29] =
                "有时候，我们明明原谅了那个人，却无法真正快乐起来。那是因为，你忘了原谅自己。《银魂》"
            quotes[30] = "所谓结婚，就是把错误延续一生。《银魂》"
            LLSSQuotes(list = quotes.toList())
        }

    }

}