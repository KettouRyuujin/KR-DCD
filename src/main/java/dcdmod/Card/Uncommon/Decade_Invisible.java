package dcdmod.Card.Uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;



public class Decade_Invisible extends AbstractCustomCardWithType{
	
	public static final String ID = "Decade_Invisible";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Decade_Invisible.png";
	private static final int COST = 2;
	private static final int BLOCK_AMT = 15;
	
	
	public Decade_Invisible() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
		this.baseBlock = BLOCK_AMT;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		int x = 0;
		if(p.hasPower("Dexterity") && p.getPower("Dexterity").amount > 0) {
			x = p.getPower("Dexterity").amount;
		}
		if(upgraded) {
			x = x*2;
		}
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block + x));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EntanglePower(p), 1));
	}

	@Override
    public AbstractCard makeCopy() {
        return new Decade_Invisible();
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
			this.rawDescription = EXTENDED_DESCRIPTION[0];
			initializeDescription();
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Decade_Invisible");
        NAME = Decade_Invisible.cardStrings.NAME;
        DESCRIPTION = Decade_Invisible.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Decade_Invisible.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(AbstractDungeon.player != null) {
			int x = 0;
			if(AbstractDungeon.player.hasPower("Dexterity")) {
				x = AbstractDungeon.player.getPower("Dexterity").amount;
			}
			if(upgraded) {
				x = x*2;
				this.rawDescription = EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[1] + x + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[5];
			}
			else{
				this.rawDescription = EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[1] + x + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[4];
			}
			initializeDescription();
		}
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
