package xxl.core;

public abstract class SequenceFunction extends Function {

    protected String _rangeDescription;

    public SequenceFunction(String name, String range) {
        super(name);
        _rangeDescription = range;
        Cell[] rangeCells = getCellsFromRangeDescription(_rangeDescription);
        for(Cell cell : rangeCells){
            cell.addObserver(this);
        }
        update();
    }

    public abstract void compute();

    public Cell[] getCellsFromRangeDescription(String rangeDescription)  {
        return Range.buildRange(_rangeDescription).traverse();

    }

    protected Cell getFirstCell() {
        return getCellsFromRangeDescription(_rangeDescription)[0];
    }

    protected Cell getLastCell() {
        Cell[] cells = getCellsFromRangeDescription(_rangeDescription);
        return getCellsFromRangeDescription(_rangeDescription)[cells.length-1];
    }

    @Override
    public String toString() {
            String result = value().toString() + "=" + getName() + "(" + getFirstCell().toString() + ":" + getLastCell().toString() + ")";
            return cleanStringAfterFirstEquals(result);
    }
    
}
