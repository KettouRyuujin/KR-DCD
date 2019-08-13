package dcdmod.Card.Uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.MirrorWorldPower;
import dcdmod.Relic.MirrorWorldRelic;



public class MirrorWorld extends AbstractCustomCardWithType{
	
	public static final String ID = "MirrorWorld";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/MirrorWorld.png";
	private static final int COST = 1;
	
	public MirrorWorld() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF,CardColorType.Ryuki);
		this.tags.add(DCDmod.RiderCard);
		this.baseMagicNumber = this.magicNumber = 1;
		this.exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		boolean hasRelic =false;
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MirrorWorldPower(p, this.magicNumber), this.magicNumber));
		for(AbstractRelic r1 : p.relics){
			if (r1.relicId.equals("MirrorWorldRelic")) {
				hasRelic = true;
				break;
			}
		}
		if(!hasRelic) {
			AbstractRelic r = new MirrorWorldRelic();
			AbstractDungeon.player.relics.add(r);
		}

	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(p.hasPower("MirrorWorldPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		return canUse;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new MirrorWorld();
    }
	
	@Override
	public void optionDecade() {

	}

	@Override
	public void optionKuuga() {

	}

	@Override
	public void optionAgito() {

	}

	@Override
	public void optionRyuki() {

	}

	@Override
	public void optionFaiz() {

	}

	@Override
	public void optionBlade() {

	}

	@Override
	public void optionHibiki() {

	}

	@Override
	public void optionKabuto() {

	}

	@Override
	public void optionDenO() {

	}

	@Override
	public void optionKiva() {

	}

	@Override
	public void optionNeutral() {

	}
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeBaseCost(0);
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("MirrorWorld");
        NAME = MirrorWorld.cardStrings.NAME;
        DESCRIPTION = MirrorWorld.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = MirrorWorld.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.UNCOMMON[0], DCDmod.UNCOMMON_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.UNCOMMON[6], DCDmod.UNCOMMON_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.UNCOMMON[cost], DCDmod.UNCOMMON_P[cost]);
		}
	}

	
	
}
