package it.interlogic.vimp.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;


public class PaginationHelper<E>
{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<E> fetchPage(final JdbcTemplate jt, final String sqlCountRows, final String sqlFetchRows, final Object args[], final int pageNo, final int pageSize,
			final RowMapper<E> rowMapper)
	{

		// determine how many rows are available
		//final int rowCount = jt.queryForInt(sqlCountRows, args);
		
		final int rowCount = jt.queryForObject(sqlCountRows, Integer.class, args);

		// calculate the number of pages
		int pageCount = rowCount / pageSize;
		if (rowCount > pageSize * pageCount)
		{
			pageCount++;
		}

		// create the page object
		final Page<E> page = new Page<E>();
		
		page.setNumber(pageNo);
		page.setTotalPages(pageCount);
		page.setTotalElements(rowCount);


		// fetch a single page of results
		final int startRow = (pageNo) * pageSize;
		jt.query(sqlFetchRows, args, new ResultSetExtractor()
		{
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException
			{
				final List pageItems = page.getContent();
				int currentRow = 0;
				while (rs.next() && currentRow < startRow + pageSize)
				{
					if (currentRow >= startRow)
					{
						pageItems.add(rowMapper.mapRow(rs, currentRow));
					}
					currentRow++;
				}
				return page;
			}
		});
		return page;
	}

}
