package com.shineyue.relic;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestRelic extends CustomRelic {
    /**
     * 遗物ID 随便写 但是需要和json文件名称一致
     * 比如我这里最终是 CANDY_MOD_Money 就需要最后json文件内有 CANDY_MOD_Money 的遗物信息
     */
    public static final String ID = "Samomi_TestRelic";
    /**
     * 日志对象
     */
    private static final Logger log = LogManager.getLogger(TestRelic.class);

    /**
     * 构造函数
     */
    public TestRelic() {
        //图片使用内置的 使用破碎王冠 的图标
        //使用内置图标就不需要导入了 想自定义可以抄其他的mod或者看教程
        super(ID, "crown.png", RelicTier.COMMON, LandingSound.CLINK);
    }

    /**
     * 在战斗开始时触发
     */
    @Override
    public void atBattleStartPreDraw() {
        super.atBattleStartPreDraw();
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 5));
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard){
        super.onCardDraw(drawnCard);
        AbstractCreature monster = AbstractDungeon.getMonsters().getRandomMonster();
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(monster, this));
        AbstractDungeon.actionManager.addToTop(new DamageAction(monster,new DamageInfo(monster, 5, DamageInfo.DamageType.THORNS)));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
