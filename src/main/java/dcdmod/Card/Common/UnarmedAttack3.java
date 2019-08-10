package dcdmod.Card.Common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.KuugaSpecialPower;
import dcdmod.Power.UnarmedAttack3Power;
import dcdmod.Vfx.Kuuga_UnarmedAttack3;
import dcdmod.Vfx.Kuuga_UnarmedAttack3_U;


public class UnarmedAttack3 extends AbstractCustomCardWithType{
	
	public static final String ID = "UnarmedAttack3";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/UnarmedAttack3.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	
	
	public UnarmedAttack3() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
		this.tags.add(DCDmod.UnarmedCard);
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = 2;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if(upgraded) {
			if(p.hasPower("KamenRideKuugaPower")){
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_UnarmedAttack3_U(),0.0F));
				AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), this.damageType, AttackEffect.NONE));
				for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
					if ((!monster.isDead) && (!monster.isDying)) {
						if(p.hasPower("RisingMightyPower")){
							AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster,p, new KuugaSpecialPower(monster,1), 1));
						}
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster,p, new KuugaSpecialPower(monster,1), 1));
					}
				}
			}
			else{
				AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), this.damageType, AttackEffect.BLUNT_HEAVY));
			}
			for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
				if ((!monster.isDead) && (!monster.isDying)) {
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, monster, new UnarmedAttack3Power(monster, 1), 1));
				}
			}
		}
		else {
			if(p.hasPower("KamenRideKuugaPower")){
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_UnarmedAttack3(m),0.0F));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageType)));
				if(p.hasPower("RisingMightyPower")){
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p, new KuugaSpecialPower(m,1), 1));
				}
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p, new KuugaSpecialPower(m,1), 1));
			}
			else{
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageType), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new UnarmedAttack3Power(m, 1), 1));
		}
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
		if(AbstractDungeon.player.hasPower("BladeBeatPower")) {
			this.damage += AbstractDungeon.player.getPower("BladeBeatPower").amount * 2;
			this.isDamageModified = true;
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new UnarmedAttack3();
    }
	
	@Override
	public void optionDecade() {
		this.damageType = DamageType.NORMAL;
		setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
	}

	@Override
	public void optionKuuga() {
		if(AbstractDungeon.player.hasPower("KuugaPegasusPower")||AbstractDungeon.player.hasPower("RisingPegasusPower")) {
			this.damageType = DamageType.HP_LOSS;
		}
		setBackgroundTexture("img/512/attack_kuuga.png", "img/1024/attack_kuuga.png");
	}

	@Override
	public void optionAgito() {
	    setBackgroundTexture("img/512/attack_agito.png", "img/1024/attack_agito.png");
	}

	@Override
	public void optionRyuki() {
	    setBackgroundTexture("img/512/attack_ryuki.png", "img/1024/attack_ryuki.png");
	}

	@Override
	public void optionFaiz() {
	    setBackgroundTexture("img/512/attack_faiz.png", "img/1024/attack_faiz.png");
	}

	@Override
	public void optionBlade() {
	    setBackgroundTexture("img/512/attack_blade.png", "img/1024/attack_blade.png");
	}

	@Override
	public void optionHibiki() {
	    setBackgroundTexture("img/512/attack_hibiki.png", "img/1024/attack_hibiki.png");
	}

	@Override
	public void optionKabuto() {
	    setBackgroundTexture("img/512/attack_kabuto.png", "img/1024/attack_kabuto.png");
	}

	@Override
	public void optionDenO() {
	    setBackgroundTexture("img/512/attack_deno.png", "img/1024/attack_deno.png");
	}

	@Override
	public void optionKiva() {
	    setBackgroundTexture("img/512/attack_kiva.png", "img/1024/attack_kiva.png");
	}

	@Override
	public void optionNeutral() {
		this.damageType = DamageType.NORMAL;
		setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
	}
    
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.target = AbstractCard.CardTarget.ALL_ENEMY;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("UnarmedAttack3");
        NAME = UnarmedAttack3.cardStrings.NAME;
        DESCRIPTION = UnarmedAttack3.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = UnarmedAttack3.cardStrings.UPGRADE_DESCRIPTION;
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
