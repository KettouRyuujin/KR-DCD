package dcdmod.Card.Common;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;



public class Kuuga_PegasusBowgun extends AbstractCustomCardWithType{
	
	public static final String ID = "Kuuga_PegasusBowgun";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Kuuga_PegasusBowgun.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 8;
	private List<TooltipInfo> tips;
	
	
	public Kuuga_PegasusBowgun() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Kuuga);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[3], EXTENDED_DESCRIPTION[4]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
			if(p.hasPower("KuugaPegasusPower")||p.hasPower("RisingPegasusPower")) {
				for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
					 if ((!monster.isDead) && (!monster.isDying) && monster != m) {
						 AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(p, this.damage, DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
					 }
				}
				if(p.hasPower("RisingPegasusPower")) {
					for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
						 if ((!monster.isDead) && (!monster.isDying)) {
							 AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(p, this.damage, DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
						 }
					}
				}
			}
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
		if(AbstractDungeon.player.hasPower("BladeSlashPower")) {
			this.damage += AbstractDungeon.player.getPower("BladeSlashPower").amount * 2;
			this.isDamageModified = true;
		}
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Kuuga_PegasusBowgun();
    }
	
	@Override
	public void optionDecade() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
        initializeDescription();
	}

	@Override
	public void optionKuuga() {
		this.name = NAME;
		if(AbstractDungeon.player.hasPower("KuugaPegasusPower")) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
			loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
		}
		if(AbstractDungeon.player.hasPower("RisingPegasusPower")) {
			this.rawDescription = EXTENDED_DESCRIPTION[1];
			this.name = EXTENDED_DESCRIPTION[2];
			loadCardImage("img/cards/Rising_PegasusBowgun.png");
		}
        initializeDescription();
	}

	@Override
	public void optionAgito() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
        initializeDescription();
	}

	@Override
	public void optionRyuki() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
        initializeDescription();
	}

	@Override
	public void optionFaiz() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
        initializeDescription();
	}

	@Override
	public void optionBlade() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
        initializeDescription();
	}

	@Override
	public void optionHibiki() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
        initializeDescription();
	}

	@Override
	public void optionKabuto() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
        initializeDescription();
	}

	@Override
	public void optionDenO() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
        initializeDescription();
	}

	@Override
	public void optionKiva() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
        initializeDescription();
	}

	@Override
	public void optionNeutral() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
        initializeDescription();
	}
    
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Kuuga_PegasusBowgun");
        NAME = Kuuga_PegasusBowgun.cardStrings.NAME;
        DESCRIPTION = Kuuga_PegasusBowgun.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Kuuga_PegasusBowgun.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.COMMON[0], DCDmod.COMMON_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.COMMON[6], DCDmod.COMMON_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.COMMON[cost], DCDmod.COMMON_P[cost]);
		}
	}

	
	
}
