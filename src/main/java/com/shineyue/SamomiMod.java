package com.shineyue;

import basemod.BaseMod;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import basemod.interfaces.PostExhaustSubscriber;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author lenovo
 */
public class SamomiMod implements PostExhaustSubscriber, PostBattleSubscriber, PostDungeonInitializeSubscriber, PostDrawSubscriber {
    public static final Logger logger = LogManager.getLogger(SamomiMod.class.getName());
    private int count, totalCount;

    public SamomiMod() {
        BaseMod.subscribe(this);
        resetCounts();
    }

    public static void initialize() {
        new SamomiMod();
    }

    private void resetCounts() {
        totalCount = count = 0;
    }

    @Override
    public void receivePostExhaust(AbstractCard c) {
        count++;
        totalCount++;
    }

    @Override
    public void receivePostBattle(AbstractRoom r) {
        logger.info(count + " cards were exhausted this battle, " +
                totalCount + " cards have been exhausted so far this act.");
        count = 0;
    }

    @Override
    public void receivePostDungeonInitialize() {
        resetCounts();
    }

    @Override
    public void receivePostDraw(AbstractCard abstractCard) {
        logger.info("SamomiMod cardid:"+abstractCard.cardID);
    }
}
