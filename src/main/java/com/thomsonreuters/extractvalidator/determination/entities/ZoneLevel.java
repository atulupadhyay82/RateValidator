package com.thomsonreuters.extractvalidator.determination.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * ZoneLevel Description.
 *
 * @author Matt Godsey
 */
@Entity
@Table(name = "TB_ZONE_LEVELS")
public final class ZoneLevel
{
	private Long zoneLevelId;

	/**
	 * Denotes the zone level group ID of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _zoneLevelGroupId;

	/**
	 * Denotes the user-friendly name associated with the zone level instance.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _name;

	/**
	 * Denotes the sequence number of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _sequenceNum;


	/**
	 * Constructs a new {@code ZoneLevel}.
	 */
	public ZoneLevel()
	{
		// Hard-code the zone level group for now. This may need to change.
		_zoneLevelGroupId = -1L;
	}


	@Id
	@Column(name = "ZONE_LEVEL_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getZoneLevelId()
	{
		return zoneLevelId;
	}


	public void setZoneLevelId(final Long zoneLevelId)
	{
		this.zoneLevelId = zoneLevelId;
	}


	@Column(name = "ZONE_LEVEL_GROUP_ID", nullable = false, precision = 10, scale = 0)
	public Long getZoneLevelGroupId()
	{
		return _zoneLevelGroupId;
	}


	public void setZoneLevelGroupId(final Long zoneLevelGroupId)
	{
		_zoneLevelGroupId = zoneLevelGroupId;
	}


	@Column(name = "NAME", nullable = false, length = 50)
	public String getName()
	{
		return _name;
	}


	public void setName(final String name)
	{
		_name = name;
	}


	@Column(name = "SEQUENCE_NUM", nullable = false, precision = 10, scale = 0)
	public Long getSequenceNum()
	{
		return _sequenceNum;
	}


	public void setSequenceNum(final Long sequenceNum)
	{
		_sequenceNum = sequenceNum;
	}
}
